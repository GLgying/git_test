package com.xxx.mvn.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author:TuoTuo
 * @createDate:2022/9/19 10:53
 * @description:
 */
@Slf4j
@SpringBootTest
public class StringRedisTemplateTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void TestHash(){
        HashOperations<String, String, Object> forHash = stringRedisTemplate.opsForHash();
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
        ValueOperations<String, String> forValue = stringRedisTemplate.opsForValue();
        String s = forValue.get("xs");
        forValue.set("xx","50", Duration.ofSeconds(500));
        log.info(s);

        forValue.set("t","2");
        Long t = forValue.increment("t");
        log.info("t:{}",t);

        Long t1 = forValue.increment("t",5);
        log.info("t1:{}",t1);

        Double t2 = forValue.increment("t", 3.5);
        log.info("t2:{}",t2);

//        forValue.decrement()



        //相当于setnx ------如果存在该键则不存入，不存在则存入
        Boolean aBoolean = forValue.setIfAbsent("k1", "刘伶", 30, TimeUnit.SECONDS);
        log.info("ab：{}",aBoolean);
        Boolean aBoolea = forValue.setIfAbsent("k11", "刘伶", 30, TimeUnit.SECONDS);
        log.info("ab：{}",aBoolea);


    }

    @Test
    public void TestList(){
        ListOperations<String, String> forList = stringRedisTemplate.opsForList();
        forList.leftPush("list1", "sd");
        forList.leftPush("list1","sd5");
        forList.leftPush("list1","sd512");
    }

    @Test
    public void TestSet(){
        SetOperations<String, String> forSet = stringRedisTemplate.opsForSet();
        forSet.add("set","xs","xd");
    }



    @Test
    public void TestZSet(){
        ZSetOperations<String, String> forZSet = stringRedisTemplate.opsForZSet();
        forZSet.add("zset","sds",2);
        forZSet.add("zset","sxds",5);
        forZSet.add("zset","sdxx",8);
        forZSet.add("zset","vv",3);
    }
}
