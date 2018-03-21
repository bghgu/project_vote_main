package com.skhu.vote.service;

import com.skhu.vote.model.Req.VoteReq;

/**
 * Created by ds on 2018-03-20.
 */
public interface RestService {
    boolean boardcast(final VoteReq voteReq);
}
