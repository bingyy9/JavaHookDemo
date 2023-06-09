package com.reflect.proxy.one;

import com.reflect.proxy.common.HelloService;
import com.reflect.proxy.common.IHello;

public class MainActivityOne {
    //this left must IHello interface, given the reflection will return interface.
    public int mValue;
    IHello helloService = new HelloService();

    public void test(){
        helloService.sayHello();
        helloService.sayHello2();
    }

    public void setValue(int v){
        mValue = v;
    }
}
