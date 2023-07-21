package com.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo2 {
    public static void main(String[] args) {
        char[] chars1 = "ABCDEF".toCharArray();
        char[] chars2 = "123456".toCharArray();
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new Thread(()->{
            lock.lock();
            try{
                for (int i = 0; i < chars2.length; i++) {
                    try {
                        condition2.await();   //await和signal必须和lock锁里面处理。 await最好和while使用。
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(chars2[i]);
                    condition1.signal();
                }
            } finally {
                lock.unlock();
            }

        }, "thread2").start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            lock.lock();
            try {
                for (int i = 0; i < chars1.length; i++) {
                    System.out.println(chars1[i]);
                    try {
                        condition2.signal();
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }, "thread1").start();

    }
}
