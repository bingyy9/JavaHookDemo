package com.threadlocal;

import com.reflect.proxy.common.HelloInvocationHandler;
import com.reflect.proxy.common.IHello;
import com.reflect.proxy.two.MainActivityTwo;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class TestThreadLocal {

    public ThreadLocal<Integer> objectThreadLocal;
    public void testThreadLocal(){
        objectThreadLocal = new ThreadLocal<>();
        new Thread(new TestClient(1)).start();
        new Thread(new TestClient(2)).start();
        new Thread(new TestClient(3)).start();
    }

    public class TestClient implements Runnable{
        private int i;
        public TestClient(int i){
            this.i = i;
        }
        @Override
        public void run() {
            objectThreadLocal.set(i);
            System.out.println("thread " + Thread.currentThread().getName() + " i: " + objectThreadLocal.get());
        }
    }
}

