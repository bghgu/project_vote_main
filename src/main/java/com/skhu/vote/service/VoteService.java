package com.skhu.vote.service;

import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.Req.AuthCodeReq;
import com.skhu.vote.model.Res.DefaultRes;

import java.util.List;
import java.util.Map;

/**
 * Created by ds on 2018-01-30.
 */

public interface VoteService{
    void logout(final String code);
    DefaultRes voteService(final String code);
}
