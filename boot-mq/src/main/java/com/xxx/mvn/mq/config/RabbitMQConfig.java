package com.xxx.mvn.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *  队列参数
 *  1、name: 队列的名称；
 *  2、durable: 是否持久化
 *  3、exclusive: 是否独享、排外的（只有同一连接共享此队列，且连接断开时队列删除）
 *  4、autoDelete: 是否自动删除；
 *  5.arguments：队列的其他属性参数，有如下可选项
 *      （1）x-message-ttl：消息的过期时间，单位：毫秒；
 *      （2）x-expires：队列过期时间，队列在多长时间未被访问将被删除，单位：毫秒；
 *      （3）x-max-length：队列最大长度，超过该最大值，则将从队列头部开始删除消息；
 *      （4）x-max-length-bytes：队列消息内容占用最大空间，受限于内存大小，超过该阈值则从队列头部开始删除消息；
 *      （5）x-overflow：设置队列溢出行为。这决定了当达到队列的最大长度时消息会发生什么。有效值是drop-head、reject-publish或reject-publish-dlx。仲裁队列类型仅支持drop-head；
 *      （6）x-dead-letter-exchange：死信交换器名称，过期或被删除（因队列长度超长或因空间超出阈值）的消息可指定发送到该交换器中；
 *      （7）x-dead-letter-routing-key：死信消息路由键，在消息发送到死信交换器时会使用该路由键，如果不设置，则使用消息的原来的路由键值
 *      （8）x-single-active-consumer：表示队列是否是单一活动消费者，true时，注册的消费组内只有一个消费者消费消息，其他被忽略，false时消息循环分发给所有消费者(默认false)
 *      （9）x-max-priority：队列要支持的最大优先级数;如果未设置，队列将不支持消息优先级；
 *      （10）x-queue-mode（Lazy mode）：将队列设置为延迟模式，在磁盘上保留尽可能多的消息，以减少RAM的使用;如果未设置，队列将保留内存缓存以尽可能快地传递消息；
 *      （11）x-queue-master-locator：在集群模式下设置镜像队列的主节点信息。
 *
 *
 *       * 交换机参数
 *          *  1、name: 交换机的名称；
 *          *  2、durable: 是否持久化
 *          *  3、autoDelete: 是否自动删除；
 *          *  4.arguments：队列的其他属性参数，有如下可选项
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 四种交换机
     *  Direct Exchange（直连交换机）   Fanout Exchange（扇型交换机）  Topic Exchange（主题交换机）    Headers Exchange（头交换机）
     * 六钟模式
     *  simple简单模式      work工作模式(资源的竞争)     publish/subscribe发布订阅(共享资源)
     *  routing路由模式 topic   主题模式(路由模式的一种)     远程过程调用（RPC）
     */
//======================================================================================================================

    //队列名称
    public final static String QUEUE_A = "queue.a";
    public final static String QUEUE_B = "queue.b";
    public final static String QUEUE_C = "queue.c";
    public final static String DEAD_LETTER_QUEUE_X = "queue.x";

    //交换机名称
    public final static String EXCHANGE_A = "exchange.a";
    public final static String DEAD_LETTER_EXCHANGE_X = "exchange.x";

    @Bean
    public TopicExchange exchangeA(){
        return new TopicExchange(EXCHANGE_A);
    }
    @Bean
    public TopicExchange exchangeX(){
        return new TopicExchange(DEAD_LETTER_EXCHANGE_X);
    }

    //普通队列
    @Bean
    public Queue queueA(){
        Map arguments = new HashMap(3);
        arguments.put("x-dead-letter-exchange",DEAD_LETTER_EXCHANGE_X);
        arguments.put("x-dead-letter-routing-key","xx");
        arguments.put("x-message-ttl",5000);
        return QueueBuilder.durable(QUEUE_A).withArguments(arguments).build();
    }

    @Bean
    public Queue queueB(){
        Map arguments = new HashMap(3);
        arguments.put("x-dead-letter-exchange",DEAD_LETTER_EXCHANGE_X);
        arguments.put("x-dead-letter-routing-key","xx");
        arguments.put("x-message-ttl",10000);
        return QueueBuilder.durable(QUEUE_B).withArguments(arguments).build();
    }

    @Bean
    public Queue queueC(){
        Map arguments = new HashMap(3);
        arguments.put("x-dead-letter-exchange",DEAD_LETTER_EXCHANGE_X);
        arguments.put("x-dead-letter-routing-key","xx");
        return QueueBuilder.durable(QUEUE_C).withArguments(arguments).build();
    }
    //死信队列
    @Bean
    public Queue queueX(){
        return QueueBuilder.durable(DEAD_LETTER_QUEUE_X).build();
    }
    @Bean
    public Binding queueABindingExchangeA(){
        return BindingBuilder.bind(queueA()).to(exchangeA()).with("aa");
    }
    @Bean
    public Binding queueBBindingExchangeA(){
        return BindingBuilder.bind(queueB()).to(exchangeA()).with("ba");
    }

    @Bean
    public Binding queueCBindingExchangeA(){
        return BindingBuilder.bind(queueC()).to(exchangeA()).with("ca");
    }


    @Bean
    public Binding queueXBindingExchangeX(){
        return BindingBuilder.bind(queueX()).to(exchangeX()).with("xx");
    }





//======================================================================================================================

    public final static String DELAYED_QUEUE = "delayed.queue";
    public final static String DELAYED_EXCHANGE = "delayed.exchange";

    /**
     * 延迟队列 基于插件
     * @return
     */
    @Bean
    public CustomExchange delayedExchange(){
        Map arguments = new HashMap(1);
        arguments.put("x-delayed-type","topic");
        return new CustomExchange(DELAYED_EXCHANGE,"x-delayed-message",true,false,arguments);
    }


    @Bean
    public Queue delayedQueue(){
        return QueueBuilder.durable(DELAYED_QUEUE).build();
    }

    @Bean
    public Binding delayedQueueBindingDelayedExchange(){
        return BindingBuilder.bind(delayedQueue()).to(delayedExchange()).with("dq").noargs();
    }

//======================================================================================================================

    //发布确认
    public static final String CONFIRM_EXCHANGE_NAME = "confirm.exchange";
    public static final String CONFIRM_QUEUE_NAME = "confirm.queue";

    public static final String BACKUP_EXCHANGE_NAME = "backup.exchange";
    public static final String BACKUP_QUEUE_NAME = "backup.queue";
    public static final String WARNING_QUEUE_NAME = "warning.queue";

    public static final String CONFIRM_QUEUE_ROUTING_KEY = "cc";


    /**
     * 发布确认队列
     * @return
     */
    @Bean
    public Queue confirmQueue(){
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    /**
     * 确认交换机
     * @return
     */

    @Bean
    public DirectExchange confirmExchange(){
//        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).build();
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME)
                .durable(true)
                .withArgument("alternate-exchange",BACKUP_EXCHANGE_NAME)
                .build();
    }

    @Bean
    public Binding confirmQueueBindingConfirmExchange(){
        return BindingBuilder.bind(confirmQueue()).to(confirmExchange()).with(CONFIRM_QUEUE_ROUTING_KEY);
    }


    /**
     * 备份交换机
     * @return
     */
    @Bean
    public FanoutExchange backUpExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    /**
     * 备份队列
     * @return
     */
    @Bean
    public Queue backUpQueue(){
        return QueueBuilder.durable(BACKUP_QUEUE_NAME).build();
    }

    /**
     * 报警队列
     * @return
     */
    @Bean
    public Queue warningQueue(){
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }

    @Bean
    public Binding backUpQueueBindingBackUpExchange(){
        return BindingBuilder.bind(backUpQueue()).to(backUpExchange());
    }

    @Bean
    public Binding warningQueueBindingBackUpExchange(){
        return BindingBuilder.bind(warningQueue()).to(backUpExchange());
    }
//======================================================================================================================
    //优先级队列

    public static final String PRIORITY_EXCHANGE_NAME = "priority.exchange";

    public static final String PRIORITY_QUEUE_NAME = "priority.queue";

    public static final String PRIORITY_QUEUE_ROUTING_KEY = "pp";

    @Bean
    public TopicExchange priorityExchange(){
//        return new TopicExchange(PRIORITY_EXCHANGE_NAME);
        return ExchangeBuilder.topicExchange(PRIORITY_EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Queue priorityQueue(){
        //允许范围0-255  设置10 就是0-10 过大浪费cpu内存
        Map arguments = new HashMap(3);
        arguments.put("x-max-priority",10);
        return QueueBuilder.durable(PRIORITY_QUEUE_NAME).withArguments(arguments).build();
    }

    @Bean
    public Binding priorityQueueBindingPriorityExchange(){
        return BindingBuilder.bind(priorityQueue()).to(priorityExchange()).with(PRIORITY_QUEUE_ROUTING_KEY);
    }
//======================================================================================================================

    //懒惰队列
    public static final String LAZY_EXCHANGE_NAME = "lazy.exchange";

    public static final String LAZY_QUEUE_NAME = "lazy.queue";

    public static final String DEFAULT_QUEUE_NAME = "default.queue";

    public static final String LAZY_QUEUE_ROUTING_KEY = "lp";

    @Bean
    public FanoutExchange lazyExchange(){
//        return new TopicExchange(PRIORITY_EXCHANGE_NAME);
        return ExchangeBuilder.fanoutExchange(LAZY_EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Queue defaultQueue(){
        //默认default正常队列  正常队列保存在内存  惰性队列 保存在磁盘 惰性队列 使用场景  因宕机 维护 下线等导致的服务堆积
        Map arguments = new HashMap(1);
        arguments.put("x-queue-mode","default");  //默认可以不加
        return QueueBuilder.durable(DEFAULT_QUEUE_NAME).withArguments(arguments).build();
    }

    @Bean
    public Queue lazyQueue(){
        //默认default正常队列  正常队列保存在内存  惰性队列 保存在磁盘 惰性队列 使用场景  因宕机 维护 下线等导致的服务堆积
        Map arguments = new HashMap(1);
        arguments.put("x-queue-mode","lazy");
        return QueueBuilder.durable(LAZY_QUEUE_NAME).withArguments(arguments).build();
    }

    @Bean
    public Binding lazyQueueBindingLazyExchange(){
        return BindingBuilder.bind(lazyQueue()).to(lazyExchange());
    }
    @Bean
    public Binding defaultQueueBindingLazyExchange(){
        return BindingBuilder.bind(defaultQueue()).to(lazyExchange());
    }

//======================================================================================================================

}
