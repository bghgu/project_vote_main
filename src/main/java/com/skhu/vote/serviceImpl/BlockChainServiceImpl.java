package com.skhu.vote.serviceImpl;

import com.skhu.vote.model.Block;
import com.skhu.vote.model.Req.CandidateReq;
import com.skhu.vote.service.BlockChainService;
import com.skhu.vote.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ds on 2018-02-04.
 */

@Service
public class BlockChainServiceImpl implements BlockChainService {

    @Autowired
    SessionService sessionService;

    @Override
    public Block createBloce(CandidateReq candidateReq, String code) {
        Block block = new Block(candidateReq, code);
        return null;
    }

    @Override
    public boolean insertBlock(Block block) {
        return false;
    }

    @Override
    public boolean checkBlockChain() {
        return false;
    }
}
