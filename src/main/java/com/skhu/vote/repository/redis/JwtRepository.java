package com.skhu.vote.repository.redis;

import com.skhu.vote.model.Jwt;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ds on 2018-02-28.
 */

public interface JwtRepository extends CrudRepository<Jwt, Integer> {

}
