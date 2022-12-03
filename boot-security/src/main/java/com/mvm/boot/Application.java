package com.mvm.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author:TuoTuo
 * @createDate:2022/11/25 22:06
 * @description:
 */
@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        log.info("====启动开始====");
        SpringApplication.run(Application.class,args);
        log.info("====启动完成====");
    }
}
