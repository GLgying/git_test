package com.xxx.mvn.mq.controller;

import com.rabbitmq.client.Channel;
import com.xxx.mvn.mq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Rabbit MQ 消费者
 * @author:TuoTuo
 * @createDate:2022/8/26 23:09
 * @description:
 */
@Slf4j
@Configuration
public class MQCustomerController {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @RabbitListener(queues = RabbitMQConfig.QUEUE_A)
    public void receiveQueueA(Message message,Channel channel){
        String msg = new String(message.getBody());
        log.info("队列A：当前时间：{}，收到消息：{}",sdf.format(new Date()),msg);
    }
    @RabbitListener(queues = RabbitMQConfig.QUEUE_B)
    public void receiveQueueB(Message message,Channel channel){
        String msg = new String(message.getBody());
        log.info("队列B：当前时间：{}，收到消息：{}",sdf.format(new Date()),msg);
    }


    @RabbitListener(queues = RabbitMQConfig.DEAD_LETTER_QUEUE_X)
    public void receiveQC(Message msg, Channel channel){
        log.info("死信队列XX：当前时间：{}，收到消息：{}",sdf.format(new Date()),msg);
    }


//======================================================================================================================
    /**
     *  延迟队列 基于插件
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = RabbitMQConfig.DELAYED_QUEUE)
    public void receiveDelay(Message msg,Channel channel){
        log.info("delay延迟队列XX：当前时间：{}，收到消息：{}",sdf.format(new Date()),msg);
    }



//======================================================================================================================
    /**
     * 发布确认
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = RabbitMQConfig.CONFIRM_QUEUE_NAME)
    public void receiveConfirmQueue(Message msg,Channel channel){
        log.info("confirm发布确认XX：当前时间：{}，收到消息：{}",sdf.format(new Date()),msg);
    }

    /**
     * 备份队列
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = RabbitMQConfig.BACKUP_QUEUE_NAME)
    public void receiveBackQueue(Message msg,Channel channel){
        log.info("BACKUP备份队列：当前时间：{}，收到消息：{}",sdf.format(new Date()),msg);
    }
    /**
     * 报警队列
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = RabbitMQConfig.WARNING_QUEUE_NAME)
    public void receiveWarningQueue(Message msg,Channel channel){
        log.info("warningQueue报警队列：当前时间：{}，收到消息：{}",sdf.format(new Date()),msg);
    }
//======================================================================================================================
    /**
     * 优先级队列
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = RabbitMQConfig.PRIORITY_QUEUE_NAME)
    public void receivePriorityQueue(Message msg,Channel channel) {
        log.info("PriorityQueue优先级队列：当前时间：{}，收到消息：{}", sdf.format(new Date()), msg);
    }

//======================================================================================================================


    /**
     * 惰性队列  保存在磁盘  相对慢
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = RabbitMQConfig.LAZY_QUEUE_NAME)
    public void receiveLazyQueue(Message msg,Channel channel) {
        log.info("LazyQueue惰性队列：当前时间：{}，收到消息：{}", sdf.format(new Date()), msg);
    }
    /**
     * 默认队列  保存在内存 相对快
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = RabbitMQConfig.DEFAULT_QUEUE_NAME)
    public void receiveDefaultQueue(Message msg,Channel channel) {
        log.info("DefaultQueue默认队列：当前时间：{}，收到消息：{}", sdf.format(new Date()), msg);
    }


//======================================================================================================================
}
