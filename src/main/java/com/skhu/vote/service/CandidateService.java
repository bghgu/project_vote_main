package com.skhu.vote.service;

import com.skhu.vote.entity.CANDIDATE;

import java.util.List;

/**
 * Created by ds on 2018-01-23.
 */
public interface CandidateService {
    List<CANDIDATE> candidateList(String code);
}
