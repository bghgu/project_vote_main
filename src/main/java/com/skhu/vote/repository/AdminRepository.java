package com.skhu.vote.repository;

import com.skhu.vote.domain.ADMIN;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ds on 2018-01-31.
 */

public interface AdminRepository extends JpaRepository<ADMIN, Integer> {
    ADMIN findById(final String id);
    ADMIN findByIdAndPassword(final String id, final String password);
}
