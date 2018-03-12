package com.skhu.vote.serviceImpl;

/**
 * Created by ds on 2018-02-04.
 */

import com.skhu.vote.domain.BLOCKCHAIN;
import com.skhu.vote.model.BlockBody;
import com.skhu.vote.model.BlockHeader;
import com.skhu.vote.model.Req.CandidateReq;
import com.skhu.vote.model.Req.VoteReq;
import com.skhu.vote.model.Res.DefaultRes;
import com.skhu.vote.repository.BlockChainRepository;
import com.skhu.vote.service.BlockChainService;
import com.skhu.vote.utils.SHA512EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 블록 체인
 * 1. 블록 체크
 * 2. 블록 생성
 * 3. 블록 삽입
 * 4. 브로드 캐스트
 * 5. 블록 체크
 */

@Service
public class BlockChainServiceImpl implements BlockChainService {

    @Autowired
    BlockChainRepository blockChainRepository;

    @Value("${firstBlockHash}")
    private String firstBlockHash;

    //블록 삽입
    @Override
    public DefaultRes insertBlock(final VoteReq voteReq) {
        DefaultRes response = new DefaultRes();
        //블록체인 검사 및 마지막 블록 해쉬값 리턴
        String tempHashCode = checkBlockChain();
        if(!tempHashCode.equals("")) {
            //새로 삽입할 블록 리스트 생성
            for(CandidateReq candidateReq : voteReq.getVoteList()) {
                //블록 생성
                BlockHeader block = new BlockHeader(createBlockBody(candidateReq, voteReq.getCode()), tempHashCode);
                BLOCKCHAIN blockchain = new BLOCKCHAIN(block);
                blockChainRepository.save(blockchain);
                //마지막 블록 해쉬값 갱신
                tempHashCode = block.getMerkleHash();
            }
            if(!checkBlockChain().equals("")) {
                //commit;
                response.setMsg("투표 완료");
                return response;
            }else {
                //rollback;
                response.setMsg("투표 실패! 선관위에 문의 하세요.");
                return response;
            }
        }else {
            response.setMsg("블록체인 검증 오류!");
            return response;
        }
    }

    //블록 체인 검사 및 마지막 블록 해쉬값 리턴
    private String checkBlockChain() {
        String tempBlockHash = firstBlockHash;
        List<BLOCKCHAIN> blockchainList = blockChainRepository.findAll();
        for(BLOCKCHAIN blockchain : blockchainList) {
            BlockBody blockBody = new BlockBody(blockchain);
            if(blockBody.hash().equals(blockchain.getBlockHash())) {
                if(merkleHash(blockBody.hash()).equals(blockchain.getMerkleHash())) {
                    tempBlockHash = blockchain.getMerkleHash();
                }else {
                    tempBlockHash = "";
                    break;
                }
            }else {
                tempBlockHash = "";
                break;
            }
        }
        return tempBlockHash;
    }

    private String merkleHash(final String hash) {
        return SHA512EncryptUtils.encrypt(hash);
    }

    //블록 생성
    private BlockBody createBlockBody(final CandidateReq candidateReq, final String code) {
        return new BlockBody(candidateReq, code);
    }

}
