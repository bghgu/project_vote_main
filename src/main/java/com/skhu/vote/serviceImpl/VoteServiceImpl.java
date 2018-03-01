package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.AUTH;
import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.Req.AuthCodeReq;
import com.skhu.vote.model.Res.DefaultRes;
import com.skhu.vote.model.StatusEnum;
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

    /***
     * 1. 등록된 인증번호가 아닌지 확인
     2. 현재 인증번호로 로그인 중인지 확인
     3. 이미 투표를 진행했는지 확인
     4. 투표 및 후보자 리스트 반환
     */

    @Override
    public DefaultRes voteService(final String code) {
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

    private boolean isAuthCodeExist(final String code) {
        if(authRepository.findByAuthCode(code) == null) return false;
        else return true;
    }

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

    private boolean isVoteCheck(final String code) {
        if(authRepository.findByAuthCode(code).getVoteCheck() == 1) return true;
        else return false;
    }

    private Map<String, Object> createMap(final String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("jwt", jwtService.createToken(code, "voter"));
        return map;
    }

    private void updateLoginTime(final String code) {
        authRepository.updateLoginTime(new Date(), code);
    }

    private void updateLoginCheck(final int count, final String code) {
        authRepository.updateLoginCheck(count + 1, code);
    }

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
    }
}
