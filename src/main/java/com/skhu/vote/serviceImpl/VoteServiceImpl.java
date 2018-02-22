package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.AUTH;
import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.Req.AuthCodeReq;
import com.skhu.vote.repository.AuthRepository;
import com.skhu.vote.repository.VoteInfoRepository;
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

    /**
     * 인증 코드 유효성 검사
     * @param code
     * @return
     */
    @Override
    public boolean isAuthCodeExist(final String code) {
        if(authRepository.findByAuthCode(code) == null) return false;
        else return true;
    }

    /**
     * 투표 및 후보자 리스트
     * deptId = 전공 id
     * dpetId2 = 학부 id
     * 1 = 총선거용
     * @param code
     * @return
     */
    @Override
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
     * 투표 여부 체크
     * @param code
     * @return
     */
    @Override
    public boolean isVoteCheck(final String code) {
        if(authRepository.findByAuthCode(code).getVoteCheck() == 1) return true;
        else return false;
    }

    /**
     * code 세션 저장
     * jwt 맵 저장
     * @param code
     * @return
     */
    @Override
    public Map<String, Object> createMap(final AuthCodeReq code) {
        //sessionService.setSession(code.getCode(), code);
        Map<String, Object> map = new HashMap<>();
        map.put("jwt", jwtService.createToken(code, "voter"));
        return map;
    }

    /**
     * 로그인 시간 업데이트
     * @param code
     */
    @Override
    public void updateLoginTime(final String code) {
        authRepository.updateLoginTime(new Date(), code);
    }

    /**
     * 로그인 카운트 증가
     * @param count
     * @param code
     */
    @Override
    public void updateLoginCheck(final int count, final String code) {
        authRepository.updateLoginCheck(count + 1, code);
    }

    /**
     * 투표 여부 변경
     * @param code
     */
    @Override
    @Transactional
    public void updateVoteCheck(final String code) {
        authRepository.updateVoteCheck(code);
    }

    /**
     * 투표 로그아웃
     * @param code
     */
    @Override
    public void logout(final String code) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        //sessionService.removeSession(code);
        //sessionService.removeSession(request.getHeader("Authorization"));
    }
}
