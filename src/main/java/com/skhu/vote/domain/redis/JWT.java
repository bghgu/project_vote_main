package com.skhu.vote.domain.redis;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

/**
 * Created by ds on 2018-02-20.
 */

@Data
@RedisHash("jwt")
public class JWT {

    @Id
    private String jwt;
}
