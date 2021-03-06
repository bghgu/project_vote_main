package com.skhu.vote.controller;

import com.skhu.vote.model.*;
import com.skhu.vote.model.Req.IdReq;
import com.skhu.vote.model.Req.LoginReq;
import com.skhu.vote.model.Res.DefaultRes;
import com.skhu.vote.service.EmcService;

import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.LoginService;
import com.skhu.vote.service.RedisService;
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
    RedisService redisService;

    @PostMapping("login")
    public ResponseEntity<DefaultRes> login(@RequestBody LoginReq loginReq) {
        return new ResponseEntity<DefaultRes>(loginService.login(loginReq), HttpStatus.OK);
    }

    @GetMapping("check/{id}")
    public ResponseEntity<DefaultRes> checkUser(@PathVariable("id") final String id) {
        return new ResponseEntity<DefaultRes>(emcService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("confirm")
    @Transactional
    public ResponseEntity<DefaultRes> confirmUser(@RequestBody IdReq id) {
        return new ResponseEntity<DefaultRes>(emcService.saveAuth(id.getId()), HttpStatus.OK);
    }

    @GetMapping("logout")
    public ResponseEntity<DefaultRes> logout(HttpServletRequest request) {
        loginService.logout(jwtService.getAuthId("emc"));
        DefaultRes response = new DefaultRes(StatusEnum.SUCCESS);
        response.setMsg("안전하게 로그아웃이 되었습니다.");
        return new ResponseEntity<DefaultRes>(response, HttpStatus.OK);
    }

    @GetMapping("clear")
    public ResponseEntity<DefaultRes> clear(HttpServletRequest request) {
        redisService.clear();
        DefaultRes response = new DefaultRes(StatusEnum.SUCCESS);
        response.setMsg("redis all data clear");
        return new ResponseEntity<DefaultRes>(response, HttpStatus.OK);
    }
}
