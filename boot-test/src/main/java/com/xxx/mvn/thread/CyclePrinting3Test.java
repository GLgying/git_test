package com.xxx.mvn.thread;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author:TuoTuo
 * @createDate:2022/7/24 15:46
 * @description:
 */
public class CyclePrinting3Test {
    static Thread t1;
    static Thread t2;
    public static void main(String[] args) {
        TransferQueue q =  new LinkedTransferQueue();
        t1 =  new Thread(
                ()->{

                }
        );

        t2 =  new Thread(
                ()->{
                }
        );
        t1.start();
    }
}
