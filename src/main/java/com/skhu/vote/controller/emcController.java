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
    @PostMapping("login")
    public ResponseEntity<DefaultResponse> login(@RequestHeader("Authorization") final String jwt) {
        DefaultResponse response = new DefaultResponse();

        return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }

    @GetMapping("check/{id}")
    //@Auth
    //@RequestHeader("Authorization") final String jwt
    public ResponseEntity<DefaultResponse> checkUser(@PathVariable("id") final String id) {
        DefaultResponse response = emcService.getUser(id);
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }

    @PostMapping("confirm")
    @Transactional
    public ResponseEntity<DefaultResponse> confirmUser (@RequestBody IdRequest id) {
        DefaultResponse response = emcService.saveAuth(id.getId());
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }
}
