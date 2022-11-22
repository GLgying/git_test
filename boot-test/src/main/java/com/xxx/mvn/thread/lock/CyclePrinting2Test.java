package com.xxx.mvn.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * 交替打印二
 * @author:TuoTuo
 * @createDate:2022/7/24 13:14
 * @description:
 */
@Slf4j
public class CyclePrinting2Test {
    static Thread t1 = null;
    static Thread t2 = null;
    static Object o = new Object();
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        char[] chars1 = "abcdefg".toCharArray();
        char[] chars2 = "1234566".toCharArray();
        t1 = new Thread(()->{
            synchronized (o){
                for(char ch : chars1){
//                //能保证 打印顺序
                    log.info(String.valueOf(ch));
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //不唤醒会导致 无法退出
                o.notify();
                latch.countDown();
            }
        });
        t2 = new Thread(()->{
            synchronized (o){
                for(char ch : chars2){
                    log.info(String.valueOf(ch));
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //不唤醒会导致 无法退出
                o.notify();
                latch.countDown();
            }
        });

        t1.start();
        t2.start();
        latch.await();
        System.out.println("执行结束");
    }
}
