package com.skhu.vote.repository;

import com.skhu.vote.domain.CANDIDATE;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ds on 2018-01-23.
 */

public interface CandidateRepository extends JpaRepository<CANDIDATE, Integer>{

}
