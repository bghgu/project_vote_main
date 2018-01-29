package com.skhu.vote.repository;

import com.skhu.vote.domain.VOTEINFO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ds on 2018-01-23.
 */
public interface VoteInfoRepository extends JpaRepository<VOTEINFO, Integer> {
    VOTEINFO findByTarget(final int deptId);
    List<VOTEINFO> findByTargetOrTargetOrTarget(final int deptId, final int deptId2, final int deptId3);
}
