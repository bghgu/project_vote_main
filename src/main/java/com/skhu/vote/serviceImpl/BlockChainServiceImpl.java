package com.skhu.vote.serviceImpl;

/**
 * Created by ds on 2018-02-04.
 */

import com.skhu.vote.domain.BLOCKCHAIN;
import com.skhu.vote.model.BlockBody;
import com.skhu.vote.model.BlockHeader;
import com.skhu.vote.model.Req.CandidateReq;
import com.skhu.vote.model.Req.VoteReq;
import com.skhu.vote.repository.BlockChainRepository;
import com.skhu.vote.service.BlockChainService;
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
    public boolean insertBlock(final VoteReq voteReq) {
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
                System.out.println(blockchain.toString());
            }
            if(!checkBlockChain().equals("")) {
                //commit;
                return true;
            }else {
                //rollback;
                return false;
            }
        }else {
            return false;
        }

    }

    //블록 체인 검사 및 마지막 블록 해쉬값 리턴
    private String checkBlockChain() {
        String tempBlockHash = firstBlockHash;
        List<BLOCKCHAIN> blockchainList = blockChainRepository.findAll();
        String res = "";
        while (!blockchainList.isEmpty()) {
            String temp = tempBlockHash;
            for(BLOCKCHAIN blockchain : blockchainList) {
                if(tempBlockHash.equals(blockchain.getPreBlockHash())) {
                    tempBlockHash = blockchain.getMerkleHash();
                    blockchainList.remove(blockchain);
                }
            }
            if(temp.equals(tempBlockHash)) {
                return res;
            }
        }
        return tempBlockHash;
    }

    //마지막 블록 해쉬값 리턴
    private String getLastPreBlockHashCode() {
        String tempBlockHash = firstBlockHash;
        List<BLOCKCHAIN> blockchainList = blockChainRepository.findAll();
        for(BLOCKCHAIN blockchain : blockchainList) {

        }
        return tempBlockHash;
    }

    //블록 생성
    private BlockBody createBlockBody(final CandidateReq candidateReq, final String code) {
        return new BlockBody(candidateReq, code);
    }

}
