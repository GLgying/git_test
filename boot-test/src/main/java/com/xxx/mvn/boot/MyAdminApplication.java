package com.xxx.mvn.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Import(MyBatisConfig.class) //多个 放入 config  import一个 同下个
//@Import({MyBatisConnection.class, MyBatisTemplate.class})  //合并
public class MyAdminApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MyAdminApplication.class, args);
        SpringApplication springApplication = new SpringApplication(MyAdminApplication.class);
        springApplication.addInitializers(new MyInitializer());
        springApplication.addListeners(new MyListener());
        springApplication.run();
    }

    /**
     * 1.将bean 交给ioc
     * @return
     */
//    @Bean
//    public MyBatisConnection myBatisConnection(){
//        return new MyBatisConnection();
//    }

    // 2.放在第三方项目  由第三方项目 交给ioc  没有...   但没有自动加载
    //交给springboot容器  @Import(MyBatisConfig.class)

    //3.和第二类似  不适用@Import引入  写入 MATE-INF下spring.factories文件 中
    //以org.springframework.boot.autoconfigure.EnableAutoConfiguration为主键的值
    //springboot 自动装配


}
