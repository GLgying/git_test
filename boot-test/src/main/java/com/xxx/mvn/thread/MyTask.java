package com.xxx.mvn.thread;

/**
 * @author:TuoTuo
 * @createDate:2022/7/19 22:40
 * @description:
 */
public class MyTask implements Runnable{
//    private long a1,a2,a3,a4,a5,a6,a7;
    private long num = 0L;
//    private long b1,b2,b3,b4,b5,b6,b7;
    private static final long COUNT = 10000000000L;
    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++) {
            num++;
        }
        System.out.println(num);
    }
}
