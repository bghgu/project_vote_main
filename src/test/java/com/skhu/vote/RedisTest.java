package com.skhu.vote;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ds on 2018-02-06.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest{

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testDataHandling() throws Exception {

        String key = "key:springboot";
        redisTemplate.opsForValue().set(key, "Hello");
        String value = (String)redisTemplate.opsForValue().get(key);

        Assert.assertEquals("Hello", value);
    }
}
