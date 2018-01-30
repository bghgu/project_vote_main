package com.skhu.vote.controller;

import com.skhu.vote.model.AuthCodeRequest;
import com.skhu.vote.model.DefaultResponse;

import com.skhu.vote.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    VoteService voteService;

    //유권자 인증번호 로그인
    //유권자에 맞게 후보자 리스트 반환
    @PostMapping("access")
    public ResponseEntity<DefaultResponse> access(@RequestBody AuthCodeRequest code) {
        DefaultResponse response = new DefaultResponse();
        //코드 존재
        if(voteService.isAuthCodeExist(code.getCode())) {
            response.setMsg("success");
        }
        //코드 미존재
        else {
            response.setMsg("fail");
        }
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }

     /*if(voteService.verificationCode(code.getCode())) {
        //후보자 리스트 반환
        if(code.getCode().length() < 2) return voteService.voteList(Integer.parseInt(code.getCode()));
        else return voteService.voteList(Integer.parseInt(code.getCode().substring(0,2)));
    }else {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "FAIL");
        return jsonObject;
    }*/
}
