package com.skhu.vote.controller;

import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.IdRequest;
import com.skhu.vote.service.EmcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 * Created by ds on 2018-01-12.
 */

/**
 * 선거 관리 위원회(Election Management Committee)
 * 선관위 로그인
 * 유권자 확인
 * 인증번호 부여
 * */

@CrossOrigin
@RestController
@RequestMapping("emc")
public class emcController {

    @Autowired
    EmcService emcService;

    //선관위 로그인
    //JWT 토큰 사용
    //헤더 검사

    @GetMapping("test")
    public ResponseEntity<DefaultResponse> test2() {
        DefaultResponse response = emcService.getUser("201232016");
        //헤더값 설정 가능
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<DefaultResponse>(response, httpHeaders, HttpStatus.BAD_REQUEST);
    }


    //유권자 확인
    //유권자의 학번으로 확인
    //값이 있을 경우 사용자 정보
    //값이 없을 경우 오류 메시지
    @PostMapping("check")
    public ResponseEntity<DefaultResponse> checkUser(@RequestBody IdRequest id) {
        DefaultResponse response = emcService.getUser(id.getId());
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }

    //인증번호 부여
    //유권자 학번으로 확인
    //8자리 인증번호 반환
    @PostMapping("confirm")
    @Transactional
    public ResponseEntity<DefaultResponse> confirmUser (@RequestBody IdRequest id) {
        DefaultResponse response = emcService.saveAuth(id.getId());
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }
}
