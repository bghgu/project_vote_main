package com.skhu.vote.repository.redis;

import com.skhu.vote.domain.redis.AUTHCODE;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ds on 2018-02-20.
 */
public interface AuthCodeRepository extends CrudRepository<AUTHCODE, String> {

}
