package com.skhu.vote.service;

import org.json.simple.JSONObject;

/**
 * Created by ds on 2018-01-23.
 */
public interface VerificationService {
    JSONObject verificationCode(String code);
}