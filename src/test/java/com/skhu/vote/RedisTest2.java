package com.skhu.vote;

/**
 * Created by ds on 2018-02-06.
 */

import com.skhu.vote.model.BlockBody;
import com.skhu.vote.model.BlockHeader;
import com.skhu.vote.model.Req.CandidateReq;
import com.skhu.vote.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest2 {


    @Resource(name="redisTemplate")
    private RedisTemplate redisTemplate;

    private final static String KEY_NAME = "block";

    /*@Before
    public void initKey(){
        redisTemplate.delete(this.KEY_NAME);
    }
*/

    @Test
    public void testListOperationString() {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        listOps.leftPush(KEY_NAME,"willis");
        listOps.leftPush(KEY_NAME,"brown");
        listOps.leftPush(KEY_NAME, "endy");
        //범위 0, -1로 하면 해당 키값의 모든 값들을 담는다.
        List<String> users = listOps.range(KEY_NAME,  0,-1);
        Assert.assertEquals(3, users.size());
        for(String user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void testListOpenationObject() {
        ListOperations<String, BlockHeader> listOps = redisTemplate.opsForList();
        /*// User 인스턴스 생성
        //BlockHeader
        //listOps.rightPush(KEY_NAME, user1);
        User user2 = new User();
        user2.setUser_name("andy");
        user2.setUser_sex("F");
        //listOps.rightPush(KEY_NAME, user2);
        //List<User> users = listOps.range(KEY_NAME,0,-1);
        for( User user : users) {
            System.out.println(user.getUser_name());
            System.out.println(user.getUser_sex());
        }*/
    }


    @Test
    public void testListQueue(){
       /* ListOperations<String, User> listOps = redisTemplate.opsForList();
        // User 인스턴스 생성
        User user1 = new User();
        user1.setUser_name("willis");
        user1.setUser_sex("M");
        listOps.rightPush(KEY_NAME, user1);
        User user2 = new User();
        user2.setUser_name("andy");
        user2.setUser_sex("F");
        listOps.rightPush(KEY_NAME, user2);
        // FIFO(First In First Out) left push 후 right pop
        User popUser1 = listOps.leftPop(KEY_NAME);
        System.out.println(popUser1.getUser_name());
        System.out.println(popUser1.getUser_sex());
        Assert.assertEquals("willis", popUser1.getUser_name());
        // List Key에는 총개의 값, pop으로 하나 꺼내왔으므로 Queue에는 하나만 남아있음
        Assert.assertEquals(1, listOps.size(KEY_NAME).intValue());*/
        ListOperations<String, BlockHeader> listOps = redisTemplate.opsForList();
        // FIFO(First In First Out) left push 후 right pop
        List<BlockHeader> blockHeaderList = listOps.range(KEY_NAME, 0, -1);
        System.out.println(blockHeaderList.size());
    }

    //모든 데이터 get
    @Test
    public void testListGet() {
        ListOperations<String, BlockHeader> listOperations = redisTemplate.opsForList();
        List<BlockHeader> blockHeaderList = listOperations.range(KEY_NAME, 0, -1);
        System.out.println(blockHeaderList.size());
        for(BlockHeader blockHeader : blockHeaderList) {
            System.out.println(blockHeader.toString());
        }
    }

    //데이터 push
    @Test
    public void testListPush(){
        //ListOperations<String, User> listOperations = redisTemplate.opsForList();
        ListOperations<String, BlockHeader> listOperations = redisTemplate.opsForList();
        CandidateReq candidateReq = new CandidateReq(1, 1);
        BlockBody blockBody = new BlockBody(candidateReq, "00000000");
        BlockHeader block = new BlockHeader(blockBody, "ac84b4be39b1acc4ab1eef63187ebc59dbe16ac9c984fe2fbb424ce50f903eb13df920f73c486c41db7d5f11ed9405b5a0938f2491dfc43b94fcedcab95a00a7");
        listOperations.rightPush(KEY_NAME, block);
        //List<User> blockHeaderList = listOperations.range(KEY_NAME, 0, -1);
        List<BlockHeader> blockHeaderList = listOperations.range(KEY_NAME, 0, -1);
        System.out.println(blockHeaderList.size());
        for(BlockHeader b : blockHeaderList) {
            System.out.println(b.toString());
        }
    }

}