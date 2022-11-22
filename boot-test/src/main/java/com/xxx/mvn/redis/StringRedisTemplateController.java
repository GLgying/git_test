package com.xxx.mvn.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author:TuoTuo
 * @createDate:2022/9/19 10:22
 * @description:
 */
@Slf4j
@SpringBootTest
public class StringRedisTemplateController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void TestString(){
        HashOperations<String, Object, Object> forHash = stringRedisTemplate.opsForHash();
        forHash.put("kex","s","xd");
        Object o = forHash.get("kex", "s");
        log.info(String.valueOf(o));
    }

}
