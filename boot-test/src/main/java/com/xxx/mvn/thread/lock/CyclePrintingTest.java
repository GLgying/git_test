package com.xxx.mvn.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * 两个线程 循环打印
 * @author:TuoTuo
 * @createDate:2022/7/23 21:51
 * @description:
 */
@Slf4j
public class CyclePrintingTest {
    static Thread t1 = null;
    static Thread t2 = null;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        char[] chars1 = "abcdefghigk".toCharArray();
        char[] chars2 = "123457890".toCharArray();
         t1 = new Thread(()->{
            for(char ch:chars1){
                log.info(String.valueOf(ch));
                //唤醒t2
                LockSupport.unpark(t2);
                //阻塞自己
                LockSupport.park();
            }
            latch.countDown();
            LockSupport.park();
             System.out.println(t1.getState());
             System.out.println(t2.getState());
        });
       t2 = new Thread(()->{
            for(char ch:chars2){
                //阻塞自己
                LockSupport.park();
                log.info(String.valueOf(ch));
                //唤醒t1
                LockSupport.unpark(t1);
            }
            latch.countDown();
            LockSupport.park();
           System.out.println("++"+t1.getState());
           System.out.println("++"+t2.getState());
//           LockSupport.park();

        });
        t1.start();
        t2.start();
        latch.await();
        System.out.println("55");

    }
}
