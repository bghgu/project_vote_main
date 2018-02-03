package com.skhu.vote.serviceImpl;

import com.skhu.vote.service.RedisService;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ds on 2018-02-04.
 */

@Service
public class RedisServiceImpl implements RedisService {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    public long getVisitCount() {
        long count = 0L;
        try {
            valueOperations.increment("spring:redis:visitcount", 1);
            count = Long.valueOf(valueOperations.get("spring:redis:visitcount"));
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return count;
    }
}
