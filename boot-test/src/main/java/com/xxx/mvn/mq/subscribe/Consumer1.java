package com.xxx.mvn.mq.subscribe;

import com.rabbitmq.client.Channel;
import com.xxx.mvn.mq.MqUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:TuoTuo
 * @createDate:2022/7/5 18:53
 * @description:
 */
@Slf4j
public class Consumer1 {
    private static final String SWITCH_NAME = "logs";
    public static void main(String[] args) throws  Exception{
        log.info("消费者1");
        Channel channel = MqUtils.createChannel();
        //创建交换机
        channel.exchangeDeclare(SWITCH_NAME,"fanout");
        //创建临时队列
        String queueName = channel.queueDeclare().getQueue();
        //绑定 1. 队列名  2.交换机名 3. routingKey  可为空
        channel.queueBind(queueName,SWITCH_NAME,"");
        log.info("消费者1 接收中....");
        channel.basicConsume(queueName,true,(var1 , var3)->{
            log.info("消费者1 接收消息...."+new String(var3.getBody()));
        },(var1)->{
            log.info("消费者1 取消接收消息..."+var1);
        });

    }
}
