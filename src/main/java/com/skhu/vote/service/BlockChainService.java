package com.skhu.vote.service;

import com.skhu.vote.model.Block;
import com.skhu.vote.model.Req.CandidateReq;
import com.skhu.vote.model.Req.VoteReq;

/**
 * Created by ds on 2018-02-04.
 */

public interface BlockChainService {
    //Block createBloce(final CandidateReq candidateReq, final String code);
    boolean insertBlock(final VoteReq voteReq);
    //boolean checkBlockChain();
}
