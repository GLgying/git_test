package com.xxx.mvn.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author:TuoTuo
 * @createDate:2022/8/25 20:19
 * @description:
 */
@Slf4j
@SpringBootApplication
public class MQApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MQApplication.class);
        springApplication.run(args);
    }
}
