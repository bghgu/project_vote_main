package com.skhu.vote.service;

import com.skhu.vote.model.Req.VoteReq;
import com.skhu.vote.model.Res.DefaultRes;

/**
 * Created by ds on 2018-02-04.
 */

public interface BlockChainService {
    DefaultRes insertBlock(final VoteReq voteReq);
}
