package com.xxx.mvn.thread.lock;

/**
 * 哲学家吃饭问题
 * @author:TuoTuo
 * @createDate:2022/7/23 21:18
 * @description:
 */
public class DeadlockTest {
    public static void main(String[] args) {
        Chopsticks ct0 = new Chopsticks();
        Chopsticks ct1 = new Chopsticks();
        Chopsticks ct2 = new Chopsticks();
        Chopsticks ct3 = new Chopsticks();
        Chopsticks ct4 = new Chopsticks();

        Philosopher ps0 = new Philosopher(0,"张三",ct0,ct1);
        Philosopher ps1 = new Philosopher(1,"李四",ct1,ct2);
        Philosopher ps2 = new Philosopher(2,"王五",ct2,ct3);
        Philosopher ps3 = new Philosopher(3,"赵六",ct3,ct4);
        Philosopher ps4 = new Philosopher(4,"胡七",ct4,ct0);

        Thread thread0 = new Thread(ps0);
        Thread thread1 = new Thread(ps1);
        Thread thread2 = new Thread(ps2);
        Thread thread3 = new Thread(ps3);
        Thread thread4 = new Thread(ps4);

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }


}
