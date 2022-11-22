package com.xxx.mvn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author:TuoTuo
 * @createDate:2022/8/4 19:34
 * @description:
 */
@SpringBootApplication
public class ProviderApplication {
    public static void main(String[] args) {
//        SpringApplication.run(ProviderApplication.class,args);
        SpringApplication springApplication = new SpringApplication(ProviderApplication.class);
        ApplicationContextInitializer initializer = applicationContext->
            System.out.println("---初始化....");

        springApplication.addInitializers(initializer);

        ApplicationListener listener =  consumer ->
            System.out.println("---事件监听....");

        //监听器
        springApplication.addListeners(listener);
        ConfigurableApplicationContext context = springApplication.run(args);
        context.getBean("");
    }
}
