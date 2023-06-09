package com.reflect.proxy.two;

import com.reflect.proxy.common.HelloModel;

public class MainActivityTwo {
//    private HelloModel helloModel = new HelloModel();
    public void test(){
        HelloModel.getInstance().sayHello();
        HelloModel.getInstance().sayHello2();

    }
}
