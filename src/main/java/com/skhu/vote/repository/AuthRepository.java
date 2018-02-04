package com.skhu.vote.repository;

import com.skhu.vote.domain.AUTH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by ds on 2018-01-30.
 */

public interface AuthRepository extends JpaRepository<AUTH, Integer> {
    AUTH findByAuthCode(final String code);

    @Modifying
    @Query("UPDATE AUTH a set a.lastLogin = :time where a.authCode = :authCode")
    void updateLoginTime(@Param("time") final Date time, @Param("authCode") final String authCode);

    @Modifying
    @Query("UPDATE AUTH a set a.loginCheck = :count where a.authCode = :authCode")
    void updateLoginCheck(@Param("count") int count, @Param("authCode") final String authCode);

    @Modifying
    @Query("UPDATE AUTH a set a.voteCheck = 1 where a.authCode = :authCode")
    void updateVoteCheck(@Param("authCode") final String authCode);
}
