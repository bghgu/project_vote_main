package com.skhu.vote.controller;

import com.skhu.vote.entity.USER;
import com.skhu.vote.service.CheckService;
import com.skhu.vote.service.ConfirmService;
import com.skhu.vote.utils.CodeQueue;
import org.hibernate.annotations.Check;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
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
    CheckService checkService;

    @Autowired
    ConfirmService confirmService;

    //유권자 확인
    //유권자의 학번으로 확인
    //값이 있을 경우 사용자 정보
    //값이 없을 경우 오류 메시지
    @PostMapping("check")
    public JSONObject check(@RequestBody USER user) {
        return checkService.checkId(user.getId());
    }

    //인증번호 부여
    //유권자 학번으로 확인
    //6자리 인증번호 반환
    @PostMapping("confirm")
    @Transactional
    public JSONObject confirm (@RequestBody USER user) {
        return confirmService.voterConfirmation(user.getId());
    }

    @GetMapping("")
    public String test(HttpHeaders httpHeaders) {
        System.out.println(httpHeaders.toString());
        httpHeaders.getLocation();
        System.out.println(httpHeaders.getLocation());
        return "1";
    }
}
