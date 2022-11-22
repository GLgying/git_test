package com.mvn.mystart.mybatis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * 配置 属性自动绑定
 * @author Ging
 */
@ConfigurationProperties(
        prefix = "spring.my"
)// 需要进入jar
// 在AutoConfiguration自动配置类里面 引入 @EnableConfigurationProperties({MyProperties.class}) 交给ioc容器
public class MyProperties {
    private String url = "localhost";
    private String name = "admin";
    private String ip = "127.0.0.1";
    private String port = "8080";

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyProperties() {
    }

//    public MyProperties(String name) {
//        this.name = name;
//    }


    public MyProperties(String url) {
        this.url = url;
    }

    public MyProperties(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
