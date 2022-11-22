package redis.xxx.mvn.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author:TuoTuo
 * @createDate:2022/8/4 18:34
 * @description:
 */
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    private String host = "localhost";
    private int port = 6379;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
