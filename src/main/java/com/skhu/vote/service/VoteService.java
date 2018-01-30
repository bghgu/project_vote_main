package com.skhu.vote.service;

import com.skhu.vote.model.DefaultResponse;

/**
 * Created by ds on 2018-01-30.
 */

public interface VoteService{
    boolean isAuthCodeExist(final String code);
    DefaultResponse getVoteList(final String code);
}
