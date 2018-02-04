package com.skhu.vote.service;

import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.Req.AuthCodeReq;

import java.util.List;
import java.util.Map;

/**
 * Created by ds on 2018-01-30.
 */

public interface VoteService{
    boolean isAuthCodeExist(final String code);
    List<VOTEINFO> getVoteList(final String code);
    boolean isVoteCheck(final String code);
    Map<String, Object> createMap(final AuthCodeReq code);
    void updateLoginTime(final String code);
    void updateLoginCheck(final int count, final String code);
    void updateVoteCheck(final String code);
    void logout(final String code);
}
