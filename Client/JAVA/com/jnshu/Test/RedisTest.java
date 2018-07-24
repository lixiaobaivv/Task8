package com.jnshu.Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:redis-context.xml")
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testSpringRedis(){
        //stringRedisTemplate操作
        //String读写
        redisTemplate.delete("myStr");
        redisTemplate.opsForValue().set("myStr","skyLine");
        System.out.println("abcderfasjhjka");
        System.out.println(redisTemplate.opsForValue().get("myStr"));
        System.out.println(" -_-_-_-_-_-__-___-__-__-__-");

        //List 读写
        redisTemplate.delete("myList");
        redisTemplate.opsForList().rightPush("myList","T");
        redisTemplate.opsForList().rightPush("myList","L");
        redisTemplate.opsForList().rightPush("myList","A");
        List<String > listCache = redisTemplate.opsForList().range("myList",0,-1);
        for (String s : listCache){
            System.out.println(s);
        }
        System.out.println("__-_-_-_-_-__-___-____-______-______-________-");

        //Set读写
        redisTemplate.delete("mySet");
        redisTemplate.opsForSet().add("mySet","A");
        redisTemplate.opsForSet().add("mySet","B");
        redisTemplate.opsForSet().add("mySet","C");
        Set<String > setCache = redisTemplate.opsForSet().members("mySet");
        for (String s : setCache){
            System.out.println(s);
        }
        System.out.println("_-_-_-_-______-__________-_________________-________-");

        //Hash读写
        redisTemplate.delete("myHash");
        redisTemplate.opsForHash().put("myHash","BJ","北京");
        redisTemplate.opsForHash().put("myHash","HF","合肥");
        redisTemplate.opsForHash().put("myHash","ZG","中国");
        Map<String ,String >hashCace = redisTemplate.opsForHash().entries("myHash");
        for (Map.Entry entry : hashCace.entrySet()){
            System.out.println(entry.getKey() + "-" +entry.getValue());
        }
        System.out.println("-_-_-_____-___-__-_-");
    }
}
