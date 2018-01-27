package com.skhu.vote.serviceImpl;

import com.skhu.vote.entity.VOTEINFO;
import com.skhu.vote.repository.VoteInfoRepository;
import com.skhu.vote.service.VoteInfoService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ds on 2018-01-23.
 */

@Service
public class VoteInfoServiceImpl implements VoteInfoService {

    @Autowired
    VoteInfoRepository voteInfoRepository;

    //deptId = 전공Id
    //deptId2 = 학부Id
    public JSONObject voteList(int deptId) {
        int deptId2 = (deptId/10)*10;
        List<VOTEINFO> voteList = voteInfoRepository.findByTargetOrTargetOrTarget(deptId, deptId2, 1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "SUCCESS");
        jsonObject.put("voteList", voteList);
        return jsonObject;
    }
}
