package com.skhu.vote.service;

import com.skhu.vote.model.Res.DefaultRes;

/**
 * Created by ds on 2018-01-30.
 */

public interface EmcService {
    DefaultRes getUser(final String id);
    DefaultRes saveAuth(final String id);
}
