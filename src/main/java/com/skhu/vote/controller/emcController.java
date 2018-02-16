package com.skhu.vote.controller;

import com.skhu.vote.model.*;
import com.skhu.vote.model.Req.IdReq;
import com.skhu.vote.model.Req.LoginReq;
import com.skhu.vote.model.Res.DefaultRes;
import com.skhu.vote.service.EmcService;

import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.LoginService;
import com.skhu.vote.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<DefaultRes> login(@RequestBody LoginReq loginReq) {
        DefaultRes response = new DefaultRes();
        if(sessionService.isSession(loginReq.getId())) response.setMsg("이미 접속중입니다.");
        else {
            LoginAdmin loginAdmin = loginService.login(loginReq);
            if(loginAdmin == null) response.setMsg("로그인 실패");
            else {
                response.setStatus(StatusEnum.SUCCESS);
                response.setData(jwtService.createToken(loginAdmin, "emc"));
                response.setMsg("로그인 성공");
            }
        }
        return new ResponseEntity<DefaultRes>(response, HttpStatus.OK);
    }

    @GetMapping("check/{id}")
    public ResponseEntity<DefaultRes> checkUser(@PathVariable("id") final String id) {
        System.out.println(id);
        DefaultRes response = emcService.getUser(id);
        return new ResponseEntity<DefaultRes>(response, HttpStatus.OK);
    }

    @PostMapping("confirm")
    @Transactional
    public ResponseEntity<DefaultRes> confirmUser(@RequestBody IdReq id) {
        DefaultRes response = emcService.saveAuth(id.getId());
        return new ResponseEntity<DefaultRes>(response, HttpStatus.OK);
    }

    @GetMapping("logout")
    public ResponseEntity<DefaultRes> logout(HttpServletRequest request) {
        loginService.logout();
        DefaultRes response = new DefaultRes(StatusEnum.SUCCESS);
        response.setMsg("안전하게 로그아웃이 되었습니다.");
        return new ResponseEntity<DefaultRes>(response, HttpStatus.OK);
    }
}
