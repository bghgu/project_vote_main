package com.skhu.vote.service;

import com.skhu.vote.model.LoginAdmin;
import com.skhu.vote.model.LoginRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ds on 2018-02-02.
 */
public interface LoginService {
    LoginAdmin login(final LoginRequest loginRequest);
}