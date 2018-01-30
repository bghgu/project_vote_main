package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.AuthRepository;
import com.skhu.vote.repository.VoteInfoRepository;
import com.skhu.vote.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean isAuthCodeExist(final String code) {
        if(authRepository.findByAuthCode(code) == null) return false;
        else return true;
    }

    //deptId = 전공Id
    //deptId2 = 학부Id
    //1 = 총선거
    @Override
    public DefaultResponse getVoteList(final String code) {
        DefaultResponse response = new DefaultResponse();
        final int deptId = authRepository.findByAuthCode(code).getDepartmentId();
        final int deptId2 = (deptId / 10) * 10;
        List<VOTEINFO> voteList = voteInfoRepository.findByTargetOrTargetOrTarget(deptId, deptId2, 1);
        //List<VOTEINFO> voteList = voteInfoRepository.findByTargetOrTargetOrTarget(3, 3, 3);
        if(voteList.size() == 0) response.setMsg("투표 및 후보자 리스트가 없습니다.");
        else {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(voteList);
            response.setMsg("투표 및 후보자 리스트");
        }
        return response;
    }

}
