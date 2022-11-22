package com.xxx.mvn.mq.worker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.xxx.mvn.mq.MqUtils;
import com.xxx.mvn.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author:TuoTuo
 * @createDate:2022/7/2 22:51
 * @description:
 * 不使用 魔法值
 */
@Slf4j
public class Task1 {
    @Value("${tt.host}")
    private String host;
//    public static final String QUEUE_NAME = "DurableFair";
    public static final String QUEUE_NAME = UUIDUtil.uuid();
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Channel channel = MqUtils.createChannel();
        //开启发布确认模式
        channel.confirmSelect();
        /**
         * 生成一个队列
         * 1. 队列名
         * 2.是否持久化  false 否 内存  true 是 磁盘
         * 3.是否只提供一个消费者 是只提供一个  false 提供多个消费者
         * 4.是否 自动删除  true 自动 删除  false 不自动删除  该队列 最后一个断开连接后
         * 5.其他参数
         */
//        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
//        String message = "生产者...";
        /**
         * 发送一个消防者
         * 1.发送到那台交换机
         * 2.路由key 本次队列名
         * 3.其他参数
         * 4.发送消息的消息提
         */
        boolean exit = true;
        while(exit){
            channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            Scanner scanner = new Scanner(System.in);
            log.info("请输入发送消息");
            String message = scanner.next();

            exit = "exit".equals(message)?false:true;
//            String message = "生产者...生产中T1..."+i;
            /**
             * 发布消息
             * 1.   发送到那台交换机
             * 2.   路由key 本次队列名
             * 3.     其他参数    MessageProperties.PERSISTENT_TEXT_PLAIN  持久化
             * 4.
             */
            channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
//            channel.basicPublish("",QUEUE_NAME, null,message.getBytes());
            //发布确认 单个
             boolean confirms = channel.waitForConfirms();
             if(confirms){
                log.info("生产者  发送成功....");
             }

        }
    }

    //1.队列持久化
    //2.消息持久化
    //3.发布确认
        // 开启发布确认模式
        //3.1 单个      boolean confirms = channel.waitForConfirms();     发布一条消息 确认一次
        //3.2 批量      boolean confirms = channel.waitForConfirms();     发布完成 确认一次
        //3.3 异步
}
