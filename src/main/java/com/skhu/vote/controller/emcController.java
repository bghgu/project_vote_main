package com.skhu.vote.controller;

import com.skhu.vote.model.*;
import com.skhu.vote.service.EmcService;

import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.LoginService;
import com.skhu.vote.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    private static final String HEADER = "Authorization";

    @Autowired
    EmcService emcService;

    @Autowired
    LoginService loginService;

    @Autowired
    JwtService jwtService;

    @Autowired
    SessionService sessionService;

    @PostMapping("login")
    public ResponseEntity<DefaultResponse> login(@RequestBody LoginRequest loginRequest) {
        DefaultResponse response = new DefaultResponse();
        LoginAdmin loginAdmin = (loginService.login(loginRequest));
        if(loginAdmin == null) response.setMsg("로그인 실패");
        else {
            if(loginAdmin.isCheck()) response.setMsg("이미 접속중입니다.");
            else {
                response.setStatus(StatusEnum.SUCCESS);
                response.setData(jwtService.createToken(loginAdmin, "emc"));
                response.setMsg("로그인 성공");
            }
        }
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }

    @GetMapping("check/{id}")
    public ResponseEntity<DefaultResponse> checkUser(@PathVariable("id") final String id) {
        DefaultResponse response = emcService.getUser(id);
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }

    @PostMapping("confirm")
    @Transactional
    public ResponseEntity<DefaultResponse> confirmUser(@RequestBody IdRequest id) {
        DefaultResponse response = emcService.saveAuth(id.getId());
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }

    @GetMapping("logout")
    public ResponseEntity<DefaultResponse> logout(HttpServletRequest request) {
        sessionService.removeSession(jwtService.getAuthId("emc"));
        sessionService.removeSession(request.getHeader("Authorization"));
        DefaultResponse response = new DefaultResponse();
        response.setStatus(StatusEnum.SUCCESS);
        response.setMsg("안전하게 로그아웃이 되었습니다.");
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }
}
