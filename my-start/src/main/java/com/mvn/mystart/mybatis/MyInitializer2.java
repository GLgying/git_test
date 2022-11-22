package com.mvn.mystart.mybatis;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * 初始化器
 * @author Ging
 */
public class MyInitializer2 implements ApplicationContextInitializer<ConfigurableApplicationContext> {


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("初始化器2starter...");
    }
}
