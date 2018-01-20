package com.skhu.vote.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.skhu.vote.entity.USER;
import com.skhu.vote.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by ds on 2018-01-12.
 */

/**
 * 선거 관리 위원회(Election Management Committee)
 * 선관위 로그인
 * 유권자 확인
 * 인증번호 부여
 * */

@RestController
@RequestMapping("emc")
public class emcController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public JSONObject health() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "health");
        return jsonObject;
    }

    @PostMapping("test")
    public Object test(@RequestBody Object object) {
        System.out.println(object);
        return object;
    }

    //유권자 확인
    //유권자의 학번으로 확인
    //값이 있을 경우 사용자 정보
    //값이 없을 경우 오류 메시지
    @PostMapping("check")
    public JSONObject check(@RequestBody USER user) {
        return userService.checkId(user.getId());
    }

    //인증번호 부여
    //유권자 학번으로 확인
    //6자리 인증번호 반환
    @PostMapping("confirm")
    @Transactional
    public JSONObject confirm (@RequestBody USER user) {
        return userService.confirm(user.getId());
    }
}
