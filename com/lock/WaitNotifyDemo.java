package com.lock;

public class WaitNotifyDemo {
    static Thread t1, t2;
    public static void main(String[] args) throws InterruptedException {
        char[] chars1 = "ABCDEF".toCharArray();
        char[] chars2 = "123456".toCharArray();

        //在线程同步里面禁止玩sleep，因为sleep多久不确定，影响性能。
        t2 = new Thread(()->{
            synchronized (WaitNotifyDemo.class) {
                System.out.println("t2 start");
                for (int i = 0; i < chars2.length; i++) {
                    try {
                        WaitNotifyDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(chars2[i]);
                    WaitNotifyDemo.class.notify();
                }
                WaitNotifyDemo.class.notify(); //必须，否则程序无法停止
            }
        }, "t2");
        t2.start();
        Thread.sleep(100);
        t1 = new Thread(()->{
            synchronized (WaitNotifyDemo.class){
                for (int i = 0; i < chars1.length; i++) {
                    System.out.println(chars1[i]);
                    WaitNotifyDemo.class.notify();
                    try {
                        WaitNotifyDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                WaitNotifyDemo.class.notify();//必须，否则程序无法停止
            }

        }, "t1");
        t1.start();
    }
}
