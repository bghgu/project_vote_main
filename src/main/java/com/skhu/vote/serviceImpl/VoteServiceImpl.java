package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.repository.AuthRepository;
import com.skhu.vote.repository.VoteInfoRepository;
import com.skhu.vote.service.VoteService;
import org.json.simple.JSONObject;
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
    public DefaultResponse getVoteList(final int deptId) {
        int deptId2 = (deptId / 10) * 10;
        List<VOTEINFO> voteList = voteInfoRepository.findByTargetOrTargetOrTarget(deptId, deptId2, 1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "SUCCESS");
        jsonObject.put("voteList", voteList);
        return new DefaultResponse();
    }

}
