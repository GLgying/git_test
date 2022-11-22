package com.xxx.mvn.mq.worker;

import com.rabbitmq.client.Channel;
import com.xxx.mvn.mq.MqUtils;
import com.xxx.mvn.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

/**
 * @author:TuoTuo
 * @createDate:2022/7/2 22:51
 * @description:
 */
@Slf4j
public class Task2 {
    public static final String QUEUE_NAME = UUIDUtil.uuid();
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Channel channel = MqUtils.createChannel();
        //开启发布确认
        channel.confirmSelect();
        /**
         * 生成一个队列
         * 1. 队列名
         * 2.是否持久化  false 否 内存  true 是 磁盘
         * 3.是否只提供一个消费者 是只提供一个  false 提供多个消费者
         * 4.是否 自动删除  true 自动 删除  false 不自动删除  该队列 最后一个断开连接后
         * 5.其他参数
         */
//        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//        String message = "生产者...";
        /**
         * 发送一个消防者
         * 1.发送到那台交换机
         * 2.路由key 本次队列名
         * 3.其他参数
         * 4.发送消息的消息提
         */
        /**
         * 不使用魔法值
         */
        //单个发布
        int releasesCount = 1000;
        long oneByOneStart = System.currentTimeMillis();
        for (int i = 0; i < releasesCount; i++) {
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message = "生产者...生产中T2..."+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            boolean confirms = channel.waitForConfirms();
            if(confirms){
                log.info("生产者  发送成功....");
            }
        }
        long oneByOneEnd = System.currentTimeMillis();
        log.info("单个发布确认使用的时间："+(oneByOneEnd-oneByOneStart));


        //批量发布
        long multipleStart = System.currentTimeMillis();

        for (int i = 0; i < releasesCount; i++) {
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message = "生产者...生产中T2..."+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
//            if(i%100==0){     //570
//                boolean confirms = channel.waitForConfirms();
//                if(confirms){ //
//                    log.info("生产者  发送成功...."+i);
//                }
//            }
        }
        boolean confirms = channel.waitForConfirms();
        if(confirms){
            log.info("生产者  发送成功....");//280
        }
        long multipleEnd= System.currentTimeMillis();
        log.info("批量发布确认使用的时间："+(multipleEnd-multipleStart));


    }

    @Test
    public void confirmationAsync() throws IOException, TimeoutException, InterruptedException {
        Channel channel = MqUtils.createChannel();
        //开启发布确认
        channel.confirmSelect();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);


        //线程安全有序打的一个哈希表  适用于高并发的情况下
        //1.将序号与信息进行关联
        //2.批量删除条目
        //3.支持高并发
        ConcurrentSkipListMap<Long,String> outStandingConfirms = new ConcurrentSkipListMap();

        /**
         * 监听器
         * 1.消息标记
         * 2.是否为批量确认
         */
        channel.addConfirmListener((long var1, boolean var3)->{
            log.info("消息确认成功回调函数...");
            log.info(String.valueOf(var1));
            log.info(String.valueOf(var3));
            //2.删除已经确认的消息  剩下的就是未确认的
            if(var3){
                ConcurrentNavigableMap<Long, String> confirmed = outStandingConfirms.headMap(var1);
                confirmed.clear();
            }else {
                outStandingConfirms.remove(var1);
            }
        },(long var1, boolean var3)->{
            log.info("消息确认失败回调函数...");
            log.info(String.valueOf(var1));
            log.info(String.valueOf(var3));
            String message = outStandingConfirms.get(var1);
            log.info("未确认的消息是："+message+" 未确认的tag："+var1);
            //3.打印未确认的
        });





        long confirmsAsyncStart = System.currentTimeMillis();
        int releasesCount = 1000;
        for (int i = 0; i < releasesCount; i++) {
            String message = " 异步发布确认..";
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
//           1.记录下 所有要发布发消息
            outStandingConfirms.put(channel.getNextPublishSeqNo(),message);
        }
        long confirmsAsyncEnd = System.currentTimeMillis();
        log.info("异步发布确认使用的时间："+(confirmsAsyncEnd-confirmsAsyncStart));

        log.info("====");
        Set<Map.Entry<Long,String>> map =outStandingConfirms.entrySet();
        for(Map.Entry<Long,String> m : map){
            log.info(m.getKey()+"  "+m.getValue());
        }
    }


}
