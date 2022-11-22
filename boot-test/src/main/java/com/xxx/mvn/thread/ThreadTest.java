package com.xxx.mvn.thread;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;

/**
 * @author:TuoTuo
 * @createDate:2022/7/19 22:39
 * @description:
 */
public class ThreadTest {
    public static long COUNT = 10_0000_0000L;
    private static class T{
//        public long p1,p2,p3,p4,p5,p6,p7,p8;
        public long x = 0L;
//        public long p9,p10,p11,p12,p13,p14,p15,p16;
    }
    public static T[] arr = new T[2];
    static {
        arr[0] = new T();
        arr[1] = new T();
    }
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Thread thread1 =  new Thread(()->{
            for (long i = 0; i < COUNT; i++) {
               arr[0].x = i;
            }
            latch.countDown();
        });
        Thread thread2 =  new Thread(()->{
            for (long i = 0; i < COUNT; i++) {
                arr[1].x = i;
            }
            latch.countDown();
        });
        final long startTime = System.nanoTime();
        thread1.start();
        thread2.start();
        latch.await();
        long endTime = System.nanoTime();
        //379-429
        System.out.println("time:"+(endTime-startTime)/100_0000);

        T t = new T();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
    }
}

