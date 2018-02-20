package com.skhu.vote.domain.redis;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

/**
 * Created by ds on 2018-02-20.
 */

@Data
@RedisHash("emc")
public class EMC {

    @Id
    private int id;
    private String name;
}
