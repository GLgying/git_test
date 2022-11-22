package com.xxx.mvn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;

/**
 * @author:TuoTuo
 * @createDate:2022/8/4 18:31
 * @description:
 */
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {





        SpringApplication springApplication = new SpringApplication(TestApplication.class);
        ApplicationContextInitializer initializer = applicationContext->
                System.out.println("---初始化....");

        //初始化
        springApplication.addInitializers(initializer);

        ApplicationListener listener =  consumer ->
                System.out.println("---事件监听....");

        //监听器
        springApplication.addListeners(listener);
        ConfigurableApplicationContext context = springApplication.run(args);

        ApplicationListener applicationListener = consumer->
            System.out.println("---事件监听器...");
        context.addApplicationListener(applicationListener);

//        Jedis jedis = context.getBean(Jedis.class);
//        System.out.println(jedis);
//        String set = jedis.set("test", "one day");
//        String s = jedis.get("test");
//        System.out.println(s);




    }
    
//    @Bean
//    public Jedis jedis(){
//        return new Jedis();
//    }
}
