package com.xxx.mvn.mq.worker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;
import com.xxx.mvn.mq.MqUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author:TuoTuo
 * @createDate:2022/7/2 22:46
 * @description:
 */
@Slf4j
public class Worker1 {
    @Value("${tt.host}")
    private String host;
    public static final String QUEUE_NAME = "DurableFair";
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = MqUtils.createChannel();
        /**
         * 设置是否公平发布  默认  0 公平发布   1 不公平发布
         * 预期值
         */
        int prefetchCount = 3;
        channel.basicQos(prefetchCount);
        /**
         * 消费
         * 1. 消费那个队列
         * 2.消费成功后 是否自动应该  true 自动应答  false 手动应答
         * 3.消费成功回调
         * 4.取消回调
         */
        String result = channel.basicConsume(QUEUE_NAME, false,  (String s, Delivery delivery)-> {
            //开启手动应答
            /**
             * 开启手动应答
             *  1.消息标记
             *  2.是否批量处理  ture 是 处理所有的  false 否 只处理当前的
             */
//            log.info("worker1  手动应答中  10s....");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            log.info("worker1  手动应答中  10s end....");
            log.info("worker1 接收到的消息："+new String(delivery.getBody()));
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }, (String s)-> {
            log.info("----");
        });

        log.info("消费者w1 消费成功");
        log.info("响应消息"+result);

    }


}
