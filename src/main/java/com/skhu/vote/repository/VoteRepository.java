package com.skhu.vote.repository;

import com.skhu.vote.entity.VOTE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ds on 2018-01-23.
 */
public interface VoteRepository extends JpaRepository<VOTE, Integer> {
    VOTE findByTarget(int deptId);
    List<VOTE> findByTargetOrTargetOrTarget(int deptId, int deptId2, int deptId3);
}
