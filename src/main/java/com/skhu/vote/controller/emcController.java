package com.skhu.vote.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.skhu.vote.entity.USER;
import com.skhu.vote.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("check")
    public USER check(@RequestBody USER user) {
        return userService.findById(user.getId());
    }
}
