package com.xxx.mvn.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Set;

/**
 * @author:TuoTuo
 * @createDate:2022/9/19 10:53
 * @description:
 */
@Slf4j
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void TestHash(){
        HashOperations<String, String, Object> forHash = redisTemplate.opsForHash();
        forHash.put("kex","s","xd");
        Object o = forHash.get("kex", "s");

        Set<String> keys = forHash.keys("kex");
        for (String s :keys) {
            log.info(s);
        }
        log.info(String.valueOf(o));
    }

    @Test
    public void TestString(){
          //在使用RedisTemplate前必须序列化，否则数据库会出现乱码
        redisTemplate.setKeySerializer(new StringRedisSerializer());    //给key添加序列化
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));//给value添加序列化

        //对String类型操作类
        ValueOperations forValue = redisTemplate.opsForValue();
        //如果不设置序列化，key和value默认采用jdk的序列化方式
        forValue.set("k1","陈奕迅");
        Object k1 = forValue.get("k1");
        System.out.println(k1);

        //如果不实现序列化，value默认采用jdk的序列化方式，可以给实体类加序列化接口
//        forValue.set("k3",new Dept(1,"刘念牛",23));
    }

    @Test
    public void TestList(){
        ListOperations<String, String> forList = redisTemplate.opsForList();
        forList.leftPush("list1", "sd");
        forList.leftPush("list1","sd5");
        forList.leftPush("list1","sd512");
    }

    @Test
    public void TestSet(){
        SetOperations<String, String> forSet = redisTemplate.opsForSet();
        forSet.add("set","xs","xd");
    }



    @Test
    public void TestZSet(){
        ZSetOperations<String, String> forZSet = redisTemplate.opsForZSet();
        forZSet.add("zset","sds",2);
        forZSet.add("zset","sxds",5);
        forZSet.add("zset","sdxx",8);
        forZSet.add("zset","vv",3);
    }
}
