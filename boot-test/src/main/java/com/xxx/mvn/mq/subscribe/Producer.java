package com.xxx.mvn.mq.subscribe;

import com.rabbitmq.client.*;
import com.xxx.mvn.mq.MqUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 *
 * 发布订阅模式
 * @author:TuoTuo
 * @createDate:2022/7/5 12:06
 * @description:
 */
@Slf4j
public class Producer {
    private static final String SWITCH_NAME = "logs";

    public static void main(String[] args) throws Exception{
        log.info("生产者...");
        //创建信道
        Channel channel = MqUtils.createChannel();
        //开启发布确认
        channel.confirmSelect();
        //创建监听器
        channel.addConfirmListener((long l, boolean b)->{
            log.info("发布确认回复消息..."+String.valueOf(l));
        },(long l, boolean b)->{
            log.info("发布未确认回复消息..."+String.valueOf(l));
        });
        //创建交换机  扇形 广播
        channel.exchangeDeclare(SWITCH_NAME,BuiltinExchangeType.FANOUT);
        //创建临时队列
       String queueName = channel.queueDeclare().getQueue();
       //绑定 1. 队列名  2.交换机名 3. routingKey  可为空
        channel.queueBind(queueName,SWITCH_NAME,"");
        //1. 队列名 2. 持久化 3.消息成功应答 4. 消息取消应答
//        new DeliverCallback();
//        new CancelCallback();
        boolean exit = true;
        Scanner scanner = new Scanner(System.in);
        while(exit){
            log.info("请发布消息..");
            String message = scanner.next();
            if("exit".equals(message)){
                return;
            }
            channel.basicPublish(SWITCH_NAME,queueName,MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        }

    }
    @Test
    public void consumer1() throws Exception {
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

    @Test
    public void consumer2() throws Exception {
        log.info("消费者2");
        Channel channel = MqUtils.createChannel();
        //创建交换机
        channel.exchangeDeclare(SWITCH_NAME,"fanout");
        //创建临时队列
        String queueName = channel.queueDeclare().getQueue();
        //绑定 1. 队列名  2.交换机名 3. routingKey  可为空
        channel.queueBind(queueName,SWITCH_NAME,"");
        log.info("消费者2 接收中....");
        channel.basicConsume(queueName,true,(var1 , var3)->{
            log.info("消费者2 接收消息...."+new String(var3.getBody()));
        },(var1)->{
            log.info("消费者2 取消接收消息..."+var1);
        });

    }
}
