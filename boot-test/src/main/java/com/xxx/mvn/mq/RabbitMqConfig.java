package com.xxx.mvn.mq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@AllArgsConstructor
public class RabbitMqConfig {
    /**
     * RabbitMQ的队列主题名称
     */
    public static final String RABBITMQ_KK_TOPIC="rabbitmq.kk.topic2";
    /**
     * Rabbitmq的交换机名
     */
    public static final String KK_EXCHANGE = "kk.exchange.c";
    /**
     * Rabbitmq的DIRECT交换机和队列绑定的匹配键 DirectRouting
     */
    public static final String RABBITMQ_DIRECT_ROUTING="rabbitmq.direct.routing";
    //队列 DirectQueue
    @Bean
    public Queue EventQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(RABBITMQ_KK_TOPIC,true,false,false);
    }
    //Direct交换机 起名：EventExchange
    @Bean
    DirectExchange EventDirectExchange() {
        //  return new DirectExchange("EventExchange",true,true);
        return new DirectExchange(KK_EXCHANGE,true,false);
    }
    //绑定  将队列和交换机绑定, 并设置用于匹配键：EventDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(EventQueue()).to(EventDirectExchange()).with(RABBITMQ_DIRECT_ROUTING);
    }
    // 转为json格式
    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}

