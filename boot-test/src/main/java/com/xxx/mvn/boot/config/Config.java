package com.xxx.mvn.boot.config;

import com.xxx.mvn.boot.condition.ConditionOnClass;
import com.xxx.mvn.boot.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:TuoTuo
 * @createDate:2022/7/19 20:52
 * @description:
 */
@Configuration
public class Config {

    @Bean
//    @Conditional(UserCondition.class)
    @ConditionOnClass({"org.springframework.data.redis.core.RedisTemplate","test"})
    public User user(){
        return new User();
    }

}
