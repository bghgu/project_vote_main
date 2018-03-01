package com.skhu.vote.repository.redis;

import com.skhu.vote.domain.redis.LoginAdmin;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ds on 2018-02-20.
 */
public interface LoginAdminRepository extends CrudRepository<LoginAdmin, Integer> {
    LoginAdmin findById(final String id);
}
