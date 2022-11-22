package com.xxx.mvn.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:TuoTuo
 * @createDate:2022/7/2 22:47
 * @description:
 */
@Slf4j
public class MqUtils {
    /**
     * @Value 注入
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Channel createChannel() throws IOException, TimeoutException {
        log.info("rabbitmq...");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return  channel;
    }
}
