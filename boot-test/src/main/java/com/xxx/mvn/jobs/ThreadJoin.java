package com.xxx.mvn.jobs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Ging
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(2);
        count.countDown();
        Thread thread2 =  new Thread(()->{
            System.out.println("thread2-去买菜");
        });
        Thread thread1 =  new Thread(()->{
            System.out.println("thread1-准备要做饭");
            try {
                thread2.start();
                thread2.join();
//                thread2.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1-开始做饭");
//            for (int i = 0; i < 100; i++) {
                    int i= 0;
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println("做饭中...."+i);
                    i++;
                    if(i>5){
                        Thread.currentThread().interrupt();
                        count.countDown();
                        break;
                    }
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
//            }
        });
        thread1.start();
//        thread2.start();
        count.await();
        System.out.println("开始吃饭");
    }



}



