package redis.xxx.mvn.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author:TuoTuo
 * @createDate:2022/8/4 18:34
 * @description:
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnClass(Jedis.class)
public class RedisAutoConfigure {
    @Bean
    @ConditionalOnMissingBean(Jedis.class)   //没有这个对应的bean时在加载
    public Jedis jedis(RedisProperties redisProperties){
        System.out.println("RedisAutoConfigure redis 自动加载...");
        return new Jedis(redisProperties.getHost(),redisProperties.getPort());
    }
}
