package com.skhu.vote.repository;

import com.skhu.vote.entity.USER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by ds on 2018-01-20.
 */
public interface UserRepository extends JpaRepository<USER, Integer> {
    USER findById(int id);

    @Modifying
    @Query("UPDATE USER u set u.voteCheck = 1 where u.id = :id")
    void updateCheck(@Param("id") int id);
}
