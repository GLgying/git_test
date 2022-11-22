package com.xxx.mvn.jobs;

import java.util.concurrent.*;

/**
 * @author:TuoTuo
 * @createDate:2022/8/25 10:11
 * @description:
 */
public class MyThreadPool {
    public static void main(String[] args) {
        //执行顺序  先 1.核心数执行  2.然后进入队列  在后 3.进入非核心 （总的核心数-核心数）
        ExecutorService service1 = Executors.newCachedThreadPool();//快  存在问题  cpu100%  无核心数  全部是非核心   由于无核心数 会进入 队列 该线程池队列为同步队列 会直接进入非核心  由于非核心很多  会运行很快  过多  cpu 卡死
        ExecutorService service2 = Executors.newFixedThreadPool(10);//慢 存在问题 oom 内存溢出  核心数10个  总核心数10   1. 10进入核心 第11进入队列
        ExecutorService service3 = Executors.newSingleThreadExecutor();//最慢 存在问题  oom 内存溢出
        //自定义
        ExecutorService service4 = new ThreadPoolExecutor(10,20,0,TimeUnit.DAYS,
                new ArrayBlockingQueue<>(10));//最慢 存在问题  oom 内存溢出
        for (int i = 0; i < 1000; i++) {
            if(!service4.isShutdown()){
//                service4.execute(new MyThreadTest(i));
            }
        }
        service4.shutdown();// -> shutdown
        service4.shutdownNow();// -> stop

        /**
         * 核心池
         * 最大池
         * 存活时长
         * 存活时长 单位
         * 队列
         * 线程工厂
         * 拒绝策略
         *  先 核心数执行  然后进入队列  在后 进入非核心 （总的核心数-核心数）
         *
         */
        new ThreadPoolExecutor(20,80,1, TimeUnit.DAYS,
                new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());

//        核心池   队列    其他池
        //提交优先级  核心池   队列     其他池
        //执行优先级  核心词   其他池    队列

    }
}
