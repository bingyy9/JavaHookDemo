package com.lock;

import java.util.concurrent.locks.LockSupport;

public class LockDemo3 {
    static Thread t1, t2;

    public static void main(String[] args) throws InterruptedException {
        char[] chars1 = "ABCDEF".toCharArray();
        char[] chars2 = "123456".toCharArray();

        //在线程同步里面禁止玩sleep，因为sleep多久不确定，影响性能。
        t1 = new Thread(()->{
            for (int i = 0; i < chars1.length; i++) {
                System.out.println(chars1[i]);
                System.out.println("unpark t2");
                LockSupport.unpark(t2);    //问题：两个线程异步执行，如果unpark t2, t2->LockSupport.unpark(t1); 下面一行才运行怎么办？
                // 答案：park和unpark是可以反过来调用的。可以先unpark（t1)，在调用park。效果就是park不住，继续往下执行。
                System.out.println("park t1");
                LockSupport.park();
            }
        }, "t1");

        Thread.sleep(1000);

        t2 = new Thread(()->{
            for (int i = 0; i < chars2.length; i++) {
                System.out.println("unpark t1");
                LockSupport.unpark(t1);
                System.out.println("park t2");
                LockSupport.park();
                System.out.println(chars2[i]);
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
