package com.skhu.vote.repository;

import com.skhu.vote.entity.VOTEINFO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ds on 2018-01-23.
 */
public interface VoteInfoRepository extends JpaRepository<VOTEINFO, Integer> {
    VOTEINFO findByTarget(int deptId);
    List<VOTEINFO> findByTargetOrTargetOrTarget(int deptId, int deptId2, int deptId3);
}
