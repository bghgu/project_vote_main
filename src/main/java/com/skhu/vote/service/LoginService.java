package com.skhu.vote.service;

import com.skhu.vote.model.Req.LoginReq;
import com.skhu.vote.model.Res.DefaultRes;

/**
 * Created by ds on 2018-02-02.
 */
public interface LoginService {
    DefaultRes login(final LoginReq loginReq);
    void logout(final String id);
}
