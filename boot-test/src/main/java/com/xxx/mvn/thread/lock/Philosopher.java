package com.xxx.mvn.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 哲学家
 * @author:TuoTuo
 * @createDate:2022/7/23 21:19
 * @description:
 */
@Slf4j
public class Philosopher implements Runnable{
    private int index;
    private String name;
    private Chopsticks left,right;
    @Override
    public void run() {
        int n = 2;
        if(index%n==0){
            synchronized (left){
                try {
                    TimeUnit.SECONDS.sleep(index+1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (right){
                    try {
                        TimeUnit.SECONDS.sleep(index+1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("{}哲学家{}吃到了饭",index,name);
                }
            }
        }else{
            synchronized (right){
                try {
                    TimeUnit.SECONDS.sleep(index+1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (left){
                    try {
                        TimeUnit.SECONDS.sleep(index+1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("{}哲学家{}吃到了饭",index,name);
                }
            }
        }


    }

    public Philosopher() {
    }

    public Philosopher(Chopsticks left, Chopsticks right) {
        this(0,null,left,right);
    }

    public Philosopher(int index, Chopsticks left, Chopsticks right) {
       this(index,null,left,right);
    }

    public Philosopher(int index, String name, Chopsticks left, Chopsticks right) {
        this.index = index;
        this.name = name;
        this.left = left;
        this.right = right;
    }
}
