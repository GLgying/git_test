package com.xxx.mvn.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author:TuoTuo
 * @createDate:2022/8/28 10:18
 * @description:
 */
@Slf4j
@Configuration
public class MyCallBack implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {

    //注入 因是内部类 需要注入才能生效
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }


    /**
     * 发布确认 回调函数
     * @param correlationData 相关信息
     * @param b true 成功  false 发布失败
     * @param s 失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if(b){
            log.info("发布成功：相关数据{}",correlationData);
        }else {
            log.info("发布失败：原因{}",s);
            log.info("发布失败：相关数据{}",correlationData);
        }
    }


    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("消息：{}，被交换机{}退回，退回原因：{} 路由key：{}  ",
                new String(returnedMessage.getMessage().getBody()),returnedMessage.getExchange(),
                returnedMessage.getReplyText(),returnedMessage.getRoutingKey());
    }


}
