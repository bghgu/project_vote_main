package com.skhu.vote.serviceImpl;

import com.skhu.vote.entity.VOTE;
import com.skhu.vote.repository.VoteRepository;
import com.skhu.vote.service.VoteService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ds on 2018-01-23.
 */

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    //deptId = 전공Id
    //deptId2 = 학부Id
    public JSONObject voteList(int deptId) {
        int deptId2 = (deptId/10)*10;
        /*List<VOTE> voteList = new ArrayList<>();
        voteList.add(voteRepository.findByTarget(1));
        voteList.add(voteRepository.findByTarget(deptId));
        voteList.add(voteRepository.findByTarget(deptId2));*/
        List<VOTE> voteList = voteRepository.findByTargetOrTargetOrTarget(deptId, deptId2, 1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "SUCCESS");
        jsonObject.put("voteList", voteList);
        return jsonObject;
    }
}
