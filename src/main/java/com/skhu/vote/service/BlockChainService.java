package com.skhu.vote.service;

import com.skhu.vote.model.Req.VoteReq;

/**
 * Created by ds on 2018-02-04.
 */

public interface BlockChainService {
    boolean insertBlock(final VoteReq voteReq);
}
