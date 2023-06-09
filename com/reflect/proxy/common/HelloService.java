package com.reflect.proxy.common;

import com.reflect.proxy.common.IHello;

public class HelloService implements IHello {

    @Override
    public void sayHello() {
        System.out.println("Hello World...");
    }

    @Override
    public void sayHello2() {
        System.out.println("Hello World2...");
    }
}
