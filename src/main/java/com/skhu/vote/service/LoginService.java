package com.skhu.vote.service;

import com.skhu.vote.model.LoginAdmin;
import com.skhu.vote.model.Req.LoginReq;

/**
 * Created by ds on 2018-02-02.
 */
public interface LoginService {
    LoginAdmin login(final LoginReq loginReq);
    void logout(final String id);
}
