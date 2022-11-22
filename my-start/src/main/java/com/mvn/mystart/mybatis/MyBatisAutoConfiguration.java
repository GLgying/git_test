package com.mvn.mystart.mybatis;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 配置类
 * @author Ging
 */
@Configuration
@ComponentScan(basePackages = {"com.mvn.mystart.mybatis"})
@EnableConfigurationProperties({MyProperties.class})
public class MyBatisAutoConfiguration {

    @Bean
    @MyConditional(value = "admin")
    public MyBatisConnection myBatisConnection(){
        return new MyBatisConnection();
    }

    @Bean
    public MyBatisTemplate myBatisTemplate(){
        return new MyBatisTemplate();
    }
}
