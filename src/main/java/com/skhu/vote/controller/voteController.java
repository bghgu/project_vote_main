package com.skhu.vote.controller;

import com.skhu.vote.service.CandidateService;
import com.skhu.vote.service.VerificationService;
import com.skhu.vote.service.VoteInfoService;
import lombok.Data;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by ds on 2018-01-12.
 */

/**
 * 유권자
 * 인증번호 로그인
 * 유권자 맞춤 투표 및 후보자 리스트 반환
 * */

@CrossOrigin
@RestController
@RequestMapping("vote")
public class voteController {

    @Autowired
    VerificationService verificationService;

    @Autowired
    CandidateService candidateService;

    @Autowired
    VoteInfoService voteService;

    @Data
    public static class AuthCode {
        private String code;
    }

    //유권자 인증번호 로그인
    //유권자에 맞게 후보자 리스트 반환
    @PostMapping("access")
    public JSONObject access(@RequestBody AuthCode code) {
        if(verificationService.verificationCode(code.getCode())) {
            //후보자 리스트 반환
            if(code.getCode().length() < 2) return voteService.voteList(Integer.parseInt(code.getCode()));
            else return voteService.voteList(Integer.parseInt(code.getCode().substring(0,2)));
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "FAIL");
            return jsonObject;
        }
    }
}
