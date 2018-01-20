package com.skhu.vote.repository;

import com.skhu.vote.entity.USER;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ds on 2018-01-20.
 */
public interface UserRepository extends JpaRepository<USER, Integer> {
    USER findById(int id);
}
