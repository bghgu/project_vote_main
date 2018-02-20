package com.skhu.vote.domain.redis;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.Date;

/**
 * Created by ds on 2018-02-20.
 */

@Data
@RedisHash("authcode")
public class AUTHCODE {

    @Id
    private String code;
    private int departmentId;
    private Date lastLogin;
}
