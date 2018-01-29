package com.skhu.vote.repository;

import com.skhu.vote.domain.AUTH;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ds on 2018-01-30.
 */

public interface AuthRepository extends JpaRepository<AUTH, Integer> {
    AUTH findByAuthCode(final String code);
}
