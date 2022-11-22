package com.xxx.mvn.mq.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.xxx.mvn.mq.MqUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author:TuoTuo
 * @createDate:2022/7/7 14:44
 * @description:
 */
@Slf4j
public class Producer {
    private static final String EXCHANGE_NAME = "topic";
    private static final String QUEUE_NAME = "Q1";
    public static void main(String[] args) throws Exception{

        Channel channel = MqUtils.createChannel();
        channel.confirmSelect();
        channel.addConfirmListener((long var1, boolean var3)->{},(long var1, boolean var3)->{});
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        while (true){
            Scanner scanner = new Scanner(System.in);
            log.info("请输入：routingKey");
            String routingKey = scanner.next();
            log.info("请输入：message");
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes("UTF-8"));
            log.info("发送成功...");
        }

//        for (int i = 0; i < 10; i++) {
//            channel.basicPublish(EXCHANGE_NAME,"rang.rang.rnagx",null,"大家好".getBytes("UTF-8"));
//            log.info("发送成功...");
//        }
    }
}
