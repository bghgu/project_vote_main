package com.skhu.vote.serviceImpl;

import com.skhu.vote.model.BlockHeader;
import com.skhu.vote.model.User;
import com.skhu.vote.service.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ds on 2018-02-04.
 */

@Service
public class RedisServiceImpl implements RedisService {

    @Value("${block.hash}")
    private static String FRIST_BLOCK_HASH;

    @Resource(name="redisTemplate")
    private RedisTemplate redisTemplate;

    private final static String KEY_NAME = "project_vote:core:block";

    public void testListOpenationObject() {
        ListOperations<String, User> listOps = redisTemplate.opsForList();
        // User 인스턴스 생성
        User user1 = new User();
        user1.setUser_name("willis");
        user1.setUser_sex("M");
        listOps.rightPush(KEY_NAME, user1);
        User user2 = new User();
        user2.setUser_name("andy");
        user2.setUser_sex("F");
        listOps.rightPush(KEY_NAME, user2);
        List<User> users = listOps.range(KEY_NAME,0,-1);
        for( User user : users) {
            System.out.println(user.getUser_name());
            System.out.println(user.getUser_sex());
        }
    }

    public void testListQueue(){
        ListOperations<String, BlockHeader> listOps = redisTemplate.opsForList();
        // FIFO(First In First Out) left push 후 right pop
        List<BlockHeader> blockHeaderList = listOps.range(KEY_NAME, 0, -1);
        System.out.println(blockHeaderList.size());
    }
}
