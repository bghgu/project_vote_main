package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.AUTH;
import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.AuthRepository;
import com.skhu.vote.repository.VoteInfoRepository;
import com.skhu.vote.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by ds on 2018-01-30.
 */

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteInfoRepository voteInfoRepository;

    @Autowired
    AuthRepository authRepository;

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
}
