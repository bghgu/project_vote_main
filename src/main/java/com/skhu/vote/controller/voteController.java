package com.skhu.vote.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.skhu.vote.entity.CANDIDATE;
import com.skhu.vote.entity.VOTE;
import com.skhu.vote.service.CandidateService;
import com.skhu.vote.service.VerificationService;
import com.skhu.vote.service.VoteService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by ds on 2018-01-12.
 */

/**
 * 유권자
 * 인증번호 로그인
 * 유권자 맞춤 투표 및 후보자 리스트 반환
 * */

@RestController
@RequestMapping("vote")
public class voteController {

    @Autowired
    VerificationService verificationService;

    @Autowired
    CandidateService candidateService;

    @Autowired
    VoteService voteService;

    //유권자 인증번호 로그인
    //유권자에 맞게 후보자 리스트 반환
    @PostMapping("access")
    public JSONObject access(@RequestParam String code) {
        //인증번호 로그인 성공
        if(verificationService.verificationCode(code)) {
            //후보자 리스트 반환
            if(code.length() < 2) return voteService.voteList(Integer.parseInt(code));
            else return voteService.voteList(Integer.parseInt(code.substring(0,2)));
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "FAIL");
            return jsonObject;
        }
    }

    @PostMapping("test")
    public JSONPObject test(@RequestParam String code) {
        JSONPObject jsonpObject = new JSONPObject("message", "FAIL");
        return jsonpObject;
    }
}
