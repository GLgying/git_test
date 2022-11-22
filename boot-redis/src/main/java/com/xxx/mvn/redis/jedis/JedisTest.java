package com.xxx.mvn.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author:TuoTuo
 * @createDate:2022/9/27 9:42
 * @description:
 */
@Slf4j
public class JedisTest {

    @Test
    public void testJedis(){
        log.info("**");
        Jedis jedis = new Jedis("localhost", 6379);
        String s = jedis.get("xs");
        boolean xs = jedis.exists("xs");
        log.info("{}",xs);

        Set<String> keys = jedis.keys("*");
        for(String key:keys){
            log.info("现有keys：{}\t",key);
        }

//        StringUtils.isblank();
//        StringUtils.isBlank(keys);
//        org.junit.platform.commons.util.StringUtils.isBlank()
        log.info(s);
    }
}
