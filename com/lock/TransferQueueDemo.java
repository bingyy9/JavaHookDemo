package com.lock;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        char[] chars1 = "ABCDEF".toCharArray();
        char[] chars2 = "123456".toCharArray();
        TransferQueue<Character> transferQueue = new LinkedTransferQueue<>();
        new Thread(()->{
                System.out.println("t2 start");
                for (int i = 0; i < chars2.length; i++) {
                    try {
                        System.out.println("t2 take " + transferQueue.take());
                        System.out.println("t2 start transfer " + chars2[i]);
                        transferQueue.transfer(chars2[i]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end");
        }, "t2").start();

        new Thread(()->{
                System.out.println("t1 start");
                for (int i = 0; i < chars1.length; i++) {
                    try {
                        System.out.println("t1 start transfer " + chars1[i]);
                        transferQueue.transfer(chars1[i]);
                        System.out.println("t1 take " + transferQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1 end");
        }, "t1").start();
    }
}
