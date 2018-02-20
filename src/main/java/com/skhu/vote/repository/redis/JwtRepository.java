package com.skhu.vote.repository.redis;

import com.skhu.vote.domain.redis.JWT;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ds on 2018-02-20.
 */
public interface JwtRepository extends CrudRepository<JWT, String> {
}
