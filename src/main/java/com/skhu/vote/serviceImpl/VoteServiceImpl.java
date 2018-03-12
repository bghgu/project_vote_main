package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.AUTH;
import com.skhu.vote.domain.CANDIDATE;
import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.Req.AuthCodeReq;
import com.skhu.vote.model.Req.CandidateReq;
import com.skhu.vote.model.Req.VoteReq;
import com.skhu.vote.model.Res.DefaultRes;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.AuthRepository;
import com.skhu.vote.repository.CandidateRepository;
import com.skhu.vote.repository.VoteInfoRepository;
import com.skhu.vote.service.BlockChainService;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ds on 2018-01-30.
 */

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteInfoRepository voteInfoRepository;

    @Autowired
    AuthRepository authRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    BlockChainService blockChainService;

    @Autowired
    CandidateRepository candidateRepository;

    /**
     * 투표 및 후보자 리스트 반환 프로세스
     * @param code
     * @return
     */
    @Override
    public DefaultRes access(final String code) {
        DefaultRes response = new DefaultRes();
        if(!isAuthCodeExist(code)) response.setMsg("등록된 인증번호가 아닙니다.");
        else {
            if(isVoteCheck(code)) response.setMsg("이미 투표를 진행했습니다.");
            else {
                List<VOTEINFO> voteList = getVoteList(code);
                if(voteList == null) response.setMsg("투표 및 후보자 리스트가 없습니다.");
                else {
                    Map<String, Object> map = createMap(code);
                    map.put("list", voteList);
                    response.setStatus(StatusEnum.SUCCESS);
                    response.setData(map);
                    response.setMsg("투표 및 후보자 리스트 입니다.");
                }
            }
        }
        return response;
    }

    /**
     * 투표
     * @param voteReq
     * @return
     */
    @Override
    public DefaultRes vote(VoteReq voteReq) {
        DefaultRes response = new DefaultRes();
        if(!isAuthCodeExist(voteReq.getCode())) response.setMsg("존재하지 않는 인증코드 입니다.");
        else {
            if(isVoteCheck(voteReq.getCode())) response.setMsg("이미 투표를 진행했습니다.");
            else {
                if(voteReq.getVoteList().size() == 0) response.setMsg("투표 값이 없습니다.");
                else {
                    if(!checkCandidateReq(voteReq.getVoteList())) response.setMsg("유효하지 않은 투표 값 입니다.");
                    else {
                        //투표 값 삽입
                        try {
                            response = blockChainService.insertBlock(voteReq);
                            updateVoteCheck(voteReq.getCode());
                            logout();
                        }catch (Exception e) {

                        }
                    }
                }
            }
        }
        return response;
    }

    private boolean checkCandidateReq(final List<CandidateReq> list) {
        candidateRepository.findAll();
        for(CandidateReq candidateReq : list) {
            if(candidateReq.getVoteId() < 1 || candidateReq.getCandidateId() < 1) return false;
            CANDIDATE candidate = candidateRepository.findOne(candidateReq.getCandidateId());
            if(candidate.getVoteInfo().getVoteId() != candidateReq.getVoteId()) return false;
        }
        return true;
    }

    /**
     * 인증 코드 존재 유무
     * @param code
     * @return
     */
    private boolean isAuthCodeExist(final String code) {
        if(authRepository.findByAuthCode(code) == null) return false;
        else return true;
    }

    /**
     * 투표 및 후보자 리스트 반환
     * @param code
     * @return
     */
    @Transactional
    public List<VOTEINFO> getVoteList(final String code) {
        AUTH auth = authRepository.findByAuthCode(code);
        final int deptId = auth.getDepartmentId();
        final int deptId2 = (deptId / 10) * 10;
        List<VOTEINFO> voteList = voteInfoRepository.findByTargetOrTargetOrTarget(deptId, deptId2, 1);
        //List<VOTEINFO> voteList = voteInfoRepository.findByTargetOrTargetOrTarget(3, 3, 3);
        if(voteList.size() == 0) return null;
        else {
            try {
                updateLoginTime(code);
                updateLoginCheck(auth.getLoginCheck(), code);
            }catch (Exception e) {
                //트랜잭션 처리
            }
            return voteList;
        }
    }

    /**
     * 투표 여부 확인
     * @param code
     * @return
     */
    private boolean isVoteCheck(final String code) {
        if(authRepository.findByAuthCode(code).getVoteCheck() == 1) return true;
        else return false;
    }

    /**
     * json map 생성 및 jwt 토큰 생성
     * @param code
     * @return
     */
    private Map<String, Object> createMap(final String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("jwt", jwtService.createToken(code, "voter"));
        return map;
    }

    /**
     * 로그인 시간 업데이트
     * @param code
     */
    private void updateLoginTime(final String code) {
        AUTH auth = authRepository.findByAuthCode(code);
        auth.setLastLogin(new Date());
        authRepository.save(auth);
    }

    /**
     * 로그인 횟수 업데이트
     * @param count
     * @param code
     */
    private void updateLoginCheck(final int count, final String code) {
        AUTH auth = authRepository.findByAuthCode(code);
        auth.setLoginCheck(auth.getLoginCheck()+1);
        authRepository.save(auth);
    }

    /**
     * 투표 여부 변경
     * @param code
     */
    @Transactional
    public void updateVoteCheck(final String code) {
        AUTH auth = authRepository.findByAuthCode(code);
        auth.setVoteCheck(1);
        authRepository.save(auth);
    }

    /**
     * 투표 로그아웃
     */
    private void logout() {
        jwtService.logoutJwt();
    }
}
