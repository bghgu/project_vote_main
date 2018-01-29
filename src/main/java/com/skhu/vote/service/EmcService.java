package com.skhu.vote.service;

import com.skhu.vote.model.DefaultResponse;

/**
 * Created by ds on 2018-01-30.
 */

public interface EmcService {
    DefaultResponse getUser(final String id);
    DefaultResponse saveAuth(final String id);
}
