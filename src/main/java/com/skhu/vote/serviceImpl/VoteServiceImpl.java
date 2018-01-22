package com.skhu.vote.serviceImpl;

import com.skhu.vote.entity.VOTE;
import com.skhu.vote.repository.VoteRepository;
import com.skhu.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void voteList(int deptId) {
        int deptId2 = (deptId/10)*10;
        List<VOTE> voteList = voteRepository.findByTargetOrTargetOrTarget(deptId, deptId2, 1);
        System.out.println(voteList.size());
    }
}
