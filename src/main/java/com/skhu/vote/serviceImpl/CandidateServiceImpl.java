package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.CANDIDATE;
import com.skhu.vote.service.CandidateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ds on 2018-01-23.
 */
@Service
public class CandidateServiceImpl implements CandidateService{
    @Override
    public List<CANDIDATE> candidateList(String deptId) {
        List<CANDIDATE> candidateList = new ArrayList<>();
        return candidateList;
    }
}
