package com.skhu.vote.serviceImpl;

import com.skhu.vote.model.Block;
import com.skhu.vote.model.Req.CandidateReq;
import com.skhu.vote.model.Req.VoteReq;
import com.skhu.vote.service.BlockChainService;
import com.skhu.vote.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ds on 2018-02-04.
 */

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
    SessionService sessionService;

    //블록 생성
    private Block createBlock(final CandidateReq candidateReq, final String code) {
        Block block = new Block(candidateReq, code);
        System.out.println(block.toString());
        return null;
    }

    //블록 삽입
    @Override
    public boolean insertBlock(final VoteReq voteReq) {
        for(CandidateReq candidateReq : voteReq.getVoteList()) {
            createBlock(candidateReq, voteReq.getCode());
        }
        return false;
    }

    //블록 체인 검사
    public boolean checkBlockChain() {
        return false;
    }
}
