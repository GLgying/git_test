package com.xxx.mvn.mq.worker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;
import com.xxx.mvn.mq.MqUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author:TuoTuo
 * @createDate:2022/7/2 22:46
 * @description:
 */
@Slf4j
public class Worker2 {
    public static final String QUEUE_NAME = "DurableFair";
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = MqUtils.createChannel();
        /**
         * 设置是否公平发布  默认  0 公平发布   1 不公平发布
         * 预期值 大  执行时间长  会导致  预期值低的 执行快的 执行完了  预期值大的执行慢点  还在执行 阻塞中
         *
         * 一共发了 8条
         * 消费者1 预期值3 执行时间短10s  先执行其中3条
         * 消费者2 预期值5 执行时间长30s  会执行  同时会阻塞几条 保证执行5条
         */
        int prefetchCount = 8;
        channel.basicQos(prefetchCount);
        /**
         * 消费
         * 1. 消费那个队列
         * 2.消费成功后 是否自动应该  true 自动应答  false 手动应答
         * 3.消费成功回调
         * 4.取消回调
         */
        String result = channel.basicConsume(QUEUE_NAME, false,  (String s, Delivery delivery)-> {
//            log.info("worker2 应答中 30s.  ");
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            log.info("worker2 应答中 30s end  ");
            log.info("worker2 接收到的消息："+new String(delivery.getBody()));
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }, (String s)-> {
            log.info("----");
        });
        log.info("消费者w2 消费成功");
        log.info("响应消息"+result);
    }
}
