package com.mvn.mystart.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 具体实现 连接器
 * @author Ging
 */
@ComponentScan
public class MyBatisConnection {

    @Autowired
    MyProperties myProperties;
    public void connection(){
        System.out.println("连接成功..."+myProperties.getName()+"---"+myProperties.getUrl()+"-" +
                ""+myProperties.getIp()+"--"+myProperties.getPort());
    }
}
