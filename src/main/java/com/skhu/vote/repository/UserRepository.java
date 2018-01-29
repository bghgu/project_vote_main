package com.skhu.vote.repository;

import com.skhu.vote.domain.USER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by ds on 2018-01-20.
 */

public interface UserRepository extends JpaRepository<USER, Integer> {
    USER findById(final String id);

    @Modifying
    @Query("UPDATE USER u set u.confirmCheck = 1 where u.id = :id")
    void updateCheck(@Param("id") final String id);
}
