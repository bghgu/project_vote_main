package com.skhu.vote.controller;

import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.Req.AuthCodeReq;
import com.skhu.vote.model.Req.CandidateReq;
import com.skhu.vote.model.Req.VoteReq;
import com.skhu.vote.model.Res.DefaultRes;

import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.service.BlockChainService;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    JwtService jwtService;

    @Autowired
    BlockChainService blockChainService;

    @PostMapping("access")
    public ResponseEntity<DefaultRes> access(@RequestBody AuthCodeReq code) {
        return new ResponseEntity<DefaultRes>(voteService.access(code.getCode()), HttpStatus.OK);
    }

    /**
     * 투표
     * 1. jwt 토큰 확인(인터셉터)
     * 2. code 세션 확인
     * 3. 넘어온 투표 값 확인(안넘어온것들은 모두 기권 처리)
     * 4. 투표 값 삽입
     * 5. 투표 여부 체크
     * 6. 투표 세션 로그아웃 처리
     * @param voteReq
     * @return
     */
    @PostMapping("")
    public ResponseEntity<DefaultRes> vote(@RequestBody VoteReq voteReq) {
        return new ResponseEntity<DefaultRes>(voteService.vote(voteReq), HttpStatus.OK);
    }

    @PostMapping("test")
    public ResponseEntity<DefaultRes> test(@RequestBody VoteReq voteReq) {
        DefaultRes response = new DefaultRes();
        blockChainService.insertBlock(voteReq);
        return new ResponseEntity<DefaultRes>(response, HttpStatus.OK);
    }
}
