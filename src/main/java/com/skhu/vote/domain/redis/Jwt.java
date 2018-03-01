package com.skhu.vote.domain.redis;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ds on 2018-02-20.
 */

@Data
@NoArgsConstructor
@RedisHash("jwt")
public class Jwt implements Serializable {

    private static final long serialVersionUID = 5443785719926497791L;

    @Id
    @Indexed
    private String jwt;

    private Date createTime;

    public Jwt(final String jwt) {
        this.jwt = jwt;
        this.createTime = new Date();
    }
}
