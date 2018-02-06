package com.skhu.vote.controller;

import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.Req.AuthCodeReq;
import com.skhu.vote.model.Req.CandidateReq;
import com.skhu.vote.model.Req.VoteReq;
import com.skhu.vote.model.Res.DefaultRes;

import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.service.BlockChainService;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.SessionService;
import com.skhu.vote.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @Autowired
    BlockChainService blockChainService;

    /**
     * 1. 인증 코드로 현재 토큰이 발급되있는지 검사(로그인 체크)
     * 2. 인증 코드값이 유효한지 검사(DB에서)
     * 3. 이미 투표를 했는지 검사
     * 4. 인증 코드에 해당하는 투표 및 후보자 리스트 반환(로그인 시간, 로그인 카운트 증가)
     * 5. 인증 코드에 해당 하는 토큰 생성
     * @param code
     * @return
     */
    @PostMapping("access")
    public ResponseEntity<DefaultRes> access(@RequestBody AuthCodeReq code) {
        DefaultRes response = new DefaultRes();
        if(sessionService.isSession(code.getCode())) response.setMsg("현재 로그인 중입니다.");
        else {
            if(!voteService.isAuthCodeExist(code.getCode())) response.setMsg("등록된 인증번호가 아닙니다.");
            else {
                if(voteService.isVoteCheck(code.getCode())) response.setMsg("이미 투표를 진행했습니다.");
                else {
                    List<VOTEINFO> voteList = voteService.getVoteList(code.getCode());
                    if(voteList == null) response.setMsg("투표 및 후보자 리스트가 없습니다.");
                    else {
                        Map<String, Object> map = voteService.createMap(code);
                        map.put("list", voteList);
                        response.setStatus(StatusEnum.SUCCESS);
                        response.setData(map);
                        response.setMsg("투표 및 후보자 리스트 입니다.");
                    }
                }
            }
        }
        return new ResponseEntity<DefaultRes>(response, HttpStatus.OK);
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
        DefaultRes response = new DefaultRes();
        if(!sessionService.isSession(voteReq.getCode())) response.setMsg("해당 인증코드는 사용하실 수 없습니다.");
        else {
            if(!voteService.isAuthCodeExist(voteReq.getCode())) response.setMsg("존재하지 않는 인증코드 입니다.");
            else {
                if(voteService.isVoteCheck(voteReq.getCode())) response.setMsg("이미 투표를 진행했습니다.");
                else {
                    if(voteReq.getVoteList().size() == 0) response.setMsg("투표 값이 없습니다.");
                    else {
                        for(CandidateReq candidateReq : voteReq.getVoteList()) {
                            if(candidateReq.getVoteId() < 1 || candidateReq.getCandidateId() < 1) response.setMsg("유효하지 않은 투표 값 입니다.");
                            else {
                                //투표 값 삽입
                                //blockChainService.insertBlock(candidateReq, voteReq.getCode());
                                voteService.updateVoteCheck(voteReq.getCode());
                                voteService.logout(voteReq.getCode());
                                response.setStatus(StatusEnum.SUCCESS);
                                response.setMsg("투표가 성공적으로 끝났습니다.");
                            }
                        }
                    }
                }
            }
        }
        return new ResponseEntity<DefaultRes>(response, HttpStatus.OK);
    }

    @PostMapping("test")
    public ResponseEntity<DefaultRes> test(@RequestBody VoteReq voteReq) {
        DefaultRes response = new DefaultRes();
        blockChainService.insertBlock(voteReq);
        return new ResponseEntity<DefaultRes>(response, HttpStatus.OK);
    }
}
