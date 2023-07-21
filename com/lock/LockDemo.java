package com.lock;

import java.util.concurrent.locks.LockSupport;

public class LockDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        Thread t1 = new Thread(()->{
            test();
        });

        t1.start();
        Thread.sleep(1000);
        LockSupport.unpark(t1);
        Thread.sleep(1000);
        System.out.println("main end");
    }

    public static void test(){
        System.out.println("threadName: " + Thread.currentThread().getName());
        LockSupport.park();
        System.out.println("end");
    }

}
