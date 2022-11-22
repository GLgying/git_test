package com.xxx.mvn.boot;

import com.xxx.mvn.boot.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author:TuoTuo
 * @createDate:2022/7/18 21:05
 * @description:
 */
@Slf4j
@Deprecated
//@SpringBootApplication
//@Import(Payment.class)
@Import(Config.class)
public class TestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TestApplication.class, args);
//        Object redis = run.getBean("redisTemplate");
//        log.info(redis.toString());
        //实现 Condition 接口 控制 bean 是否加载 和 加载时机
        Object user = run.getBean("user");
        log.info(user.toString());
//        Object payment = run.getBean(Payment.class);
//        log.info(payment.toString());
    }
}
