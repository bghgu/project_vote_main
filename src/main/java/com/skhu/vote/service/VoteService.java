package com.skhu.vote.service;

import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.DefaultResponse;

import java.util.List;

/**
 * Created by ds on 2018-01-30.
 */

public interface VoteService{
    boolean isAuthCodeExist(final String code);
    List<VOTEINFO> getVoteList(final String code);
    void updateLoginTime(final String code);
    void updateLoginCheck(final int count, final String code);
}
