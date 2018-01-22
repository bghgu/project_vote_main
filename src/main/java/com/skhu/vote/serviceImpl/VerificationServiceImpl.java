package com.skhu.vote.serviceImpl;

import com.skhu.vote.service.VerificationService;
import com.skhu.vote.utils.CodeQueue;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by ds on 2018-01-23.
 */
@Service
public class VerificationServiceImpl implements VerificationService{

    //코드 인증
    //인증이 성공할 경우 해당 코드로 JWT 토큰 발급
    @Override
    public boolean verificationCode(String code) {
        if(CodeQueue.verification(code)) {
            return true;
        }else {
            return false;
        }
    }
}
