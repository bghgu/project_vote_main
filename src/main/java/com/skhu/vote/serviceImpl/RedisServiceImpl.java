/*
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

*/
/**
 * Created by ds on 2018-02-04.
 *//*


@Service
public class RedisServiceImpl implements RedisService {

    @Value("${block.hash}")
    private static String FRIST_BLOCK_HASH;

    @Resource(name="redisTemplate")
    private RedisTemplate redisTemplate;

    private final static String KEY_NAME = "project_vote:core:block";


    //모든 데이터 get
    public List<BlockHeader> getAllList() {
        ListOperations<String, BlockHeader> listOperations = redisTemplate.opsForList();
        List<BlockHeader> blockHeaderList = listOperations.range(KEY_NAME, 0, -1);
        return blockHeaderList;
    }

    //데이터 push
    public void pushData(final BlockHeader blockHeader){
        ListOperations<String, BlockHeader> listOperations = redisTemplate.opsForList();
        listOperations.rightPush(KEY_NAME, blockHeader);
    }
}
*/
