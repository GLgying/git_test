package com.xxx.mvn.mq.demo;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author:TuoTuo
 * @createDate:2022/8/25 9:24
 * @description:
 */
public class MyMQ {
    /**
     * Broker
     */
    private static LinkedBlockingQueue<JSONObject> broker = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        /**
         * Producer
         */
        Thread producer = new Thread(() -> {
            while (true) {
                JSONObject data1 = new JSONObject();
                JSONObject data2 = new JSONObject();
                JSONObject data3 = new JSONObject();
                JSONObject data4 = new JSONObject();
                data1.put("name", "Kangkang");
                data1.put("gender", "male");
                data1.put("age", 14);
                data2.put("name", "Michael");
                data2.put("gender", "male");
                data2.put("age", 14);
                data3.put("name", "Jane");
                data3.put("gender", "female");
                data3.put("age", 14);
                data4.put("name", "Maria");
                data4.put("gender", "female");
                data4.put("age", 15);

                JSONObject data = new JSONObject();
                Object[] array = {data1, data2, data3, data4};
                data.put("group2", array);
                broker.offer(data);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者");
        producer.start();

        /**
         * Consumer
         */
        Thread consumer = new Thread(() -> {
            while (true) {
                JSONObject data = broker.poll();
                if (data != null) {
                    System.out.println(Thread.currentThread().getName() + "获取到数据：" + data.toJSONString());
                }
            }
        }, "消费者");
        consumer.start();
    }
}
