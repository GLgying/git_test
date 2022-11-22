package com.xxx.mvn.mq.worker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;
import com.xxx.mvn.mq.MqUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:TuoTuo
 * @createDate:2022/7/2 22:46
 * @description:
 */
@Slf4j
public class Worker3 {
    public static final String QUEUE_NAME = "Hello";
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = MqUtils.createChannel();
        /**
         * 消费
         * 1. 消费那个队列
         * 2.消费成功后 是否自动应该  true 自动应答  false 手动应答
         * 3.消费成功回调
         * 4.取消回调
         */
        String result = channel.basicConsume(QUEUE_NAME, true,  (String s, Delivery delivery)-> {
            log.info("++++");
            log.info(new String(delivery.getBody()));
            log.info(new String(delivery.getProperties().getClassName()));
        }, (String s)-> {
            log.info("----");
        });
        log.info("消费者w3 消费成功");
        log.info("响应消息"+result);
    }
}
