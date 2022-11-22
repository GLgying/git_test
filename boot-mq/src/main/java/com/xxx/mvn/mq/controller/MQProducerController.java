package com.xxx.mvn.mq.controller;

import com.rabbitmq.client.AMQP;
import com.xxx.mvn.mq.config.RabbitMQConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Rabbit MQ 生产者
 * @author:TuoTuo
 * @createDate:2022/8/26 23:11
 * @description:
 */
@Slf4j
@Api(tags = "文件说明：rabbitmq发送消息")
@RestController
@RequestMapping("/mq")
public class MQProducerController {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //开始发消息  普通队列
    @ApiOperation(value = "发送消息")
    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable("message") String message){
        log.info("当前时间：{}，发送的消息：{}",sdf.format(new Date()),message);
        log.info("一个交换机绑定两个队列，同时向两个队列发送");
//        rabbitTemplate.convertAndSend("topicExchangeA","b.a","消息为："+message);
//        rabbitTemplate.convertAndSend("topicExchangeA","a.b","消息为："+message);
//        rabbitTemplate.convertAndSend("topicExchangeA","a.a","消息为："+message);
//        rabbitTemplate.convertAndSend("topicExchangeA","YD","消息为："+message);
//        rabbitTemplate.convertAndSend("X","XA","消息为："+message);
//        rabbitTemplate.convertAndSend("X","XB","消息为："+message);

        rabbitTemplate.convertAndSend("exchange_a","aa","消息为："+message);
        rabbitTemplate.convertAndSend("exchange_a","ba","消息为："+message);
    }

    //死信队列
    @GetMapping("/sendExpirationMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable("message") String message,
                        @PathVariable("ttlTime") String ttlTime){

        log.info("当前时间：{}，发送时长为：{}毫秒的消息：{}",sdf.format(new Date()),ttlTime,message);
        MessagePostProcessor messagePostProcessor =  (Message msg)->{
            msg.getMessageProperties().setExpiration(ttlTime);
            return msg;
        };
        rabbitTemplate.convertAndSend("exchange_a","ca",message, messagePostProcessor);
    }


    //延迟队列 基于插件
    @GetMapping("/sendDelayMsg/{message}/{delayTime}")
    public void sendMsg(@PathVariable("message") String message,
                        @PathVariable("delayTime") Integer delayTime){

        log.info("当前时间：{}，发送时长为：{}毫秒的消息：{}",sdf.format(new Date()),delayTime,message);
        MessagePostProcessor messagePostProcessor =  (Message msg)->{
            msg.getMessageProperties().setDelay(delayTime);
            return msg;
        };
        rabbitTemplate.convertAndSend("delayedExchange","dq",message, messagePostProcessor);
    }

    //发布确认
    @GetMapping("/sendConfirmMsg/{message}")
    public void sendConfirmMsg(@PathVariable("message") String message){
        log.info("当前时间：{}，发送d消息：{}",sdf.format(new Date()),message);
        //测试发布确认  把 交换机 写错
        CorrelationData correlationData = new CorrelationData("1");
        CorrelationData correlationData2 = new CorrelationData("2");
        rabbitTemplate.convertAndSend(RabbitMQConfig.CONFIRM_EXCHANGE_NAME,RabbitMQConfig.CONFIRM_QUEUE_ROUTING_KEY,message+"11",correlationData);
        rabbitTemplate.convertAndSend(RabbitMQConfig.CONFIRM_EXCHANGE_NAME,RabbitMQConfig.CONFIRM_QUEUE_ROUTING_KEY+"22",message+"22",correlationData2);

//        rabbitTemplate.convertAndSend("confirmExchange","cc",message,correlationData);
//
//        rabbitTemplate.convertAndSend("confirmExchange","ccdd",message,correlationData2);
    }



    //优先级
    @GetMapping("/sendPriorityMsg/{message}")
    public void sendPriorityMsg(@PathVariable("message") String message){
        log.info("发送优先队列消息 当前时间：{}，发送d消息：{}",sdf.format(new Date()),message);
        int count = 10;
        for (int i = 0; i < count; i++) {
            CorrelationData correlationData = new CorrelationData("priority"+i);
            int finalI = i;
            MessagePostProcessor messagePostProcessor = (msg)->{
                MessageProperties properties = msg.getMessageProperties();
                properties.setPriority(finalI);
                return msg;
            };
            rabbitTemplate.convertAndSend(RabbitMQConfig.PRIORITY_EXCHANGE_NAME,RabbitMQConfig.PRIORITY_QUEUE_ROUTING_KEY,
                    message+i,messagePostProcessor,correlationData);
        }
    }

    /**
     * 惰性队列
     * @param message
     */
    @GetMapping("/sendLazyMsg/{message}")
    public void sendLazyMsg(@PathVariable("message") String message){
        log.info("发送惰性队列消息 当前时间：{}，发送d消息：{}",sdf.format(new Date()),message);
        int count = 10;

        for (int i = 0; i < count; i++) {
            //默认default 保存在内存 相对快些
            //惰性队列lazy 保存在磁盘 相对慢些
            CorrelationData correlationData1 = new CorrelationData("default"+i);
            CorrelationData correlationData2 = new CorrelationData("lazy"+i);

            rabbitTemplate.convertAndSend(RabbitMQConfig.LAZY_EXCHANGE_NAME,"",
                    "lazy:"+message+i,correlationData1);
//            rabbitTemplate.convertAndSend(RabbitMQConfig.LAZY_EXCHANGE_NAME,RabbitMQConfig.PRIORITY_QUEUE_ROUTING_KEY,
//                    "default:"+message+i,correlationData2);

        }
    }
}
