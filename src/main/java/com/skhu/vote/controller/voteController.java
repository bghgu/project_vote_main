package com.skhu.vote.controller;

import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.AuthCodeRequest;
import com.skhu.vote.model.DefaultResponse;

import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.SessionService;
import com.skhu.vote.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


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

    @Autowired
    SessionService sessionService;

    @Autowired
    JwtService jwtService;

    /**
     * 1. 인증 코드로 현재 토큰이 발급되있는지 검사(로그인 체크)
     * 2. 인증 코드값이 유효한지 검사(DB에서)
     * 3. 인증 코드에 해당하는 투표 및 후보자 리스트 반환(로그인 시간, 로그인 카운트 증가)
     * 4. 인증 코드에 해당 하는 토큰 생성
     * @param code
     * @return
     */
    @PostMapping("access")
    public ResponseEntity<DefaultResponse> access(@RequestBody AuthCodeRequest code) {
        DefaultResponse response = new DefaultResponse();
        if(sessionService.isSession(code.getCode())) response.setMsg("현재 로그인 중입니다.");
        else {
            if(voteService.isAuthCodeExist(code.getCode())) {
                //후보자 리스트 리턴
                List<VOTEINFO> voteList = voteService.getVoteList(code.getCode());
                if(voteList == null) response.setMsg("투표 및 후보자 리스트가 없습니다.");
                else {
                    //인증 코드 세션 저장
                    sessionService.setSession(code.getCode(), code);
                    //토큰 발급 및 토큰 세션 저장
                    Map<String, Object> map = new HashMap<>();
                    map.put("jwt", jwtService.createToken(code, "voter"));
                    map.put("list", voteList);
                    response.setStatus(StatusEnum.SUCCESS);
                    response.setData(map);
                    response.setMsg("투표 및 후보자 리스트");
                }
            }
            else response.setMsg("등록된 인증번호가 아닙니다.");
        }
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }
}
