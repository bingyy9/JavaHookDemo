package com.reflect.proxy.three;

import com.reflect.proxy.common.HelloModel;
import com.reflect.proxy.common.IHello;
import com.reflect.proxy.common.Singleton;

public class MainActivityThree {
    private static final Singleton<IHello> IHelloSingleton =
            new Singleton<IHello>() {
                @Override
                protected IHello create() {
                    return HelloModel.getInstance();
                }
            };

    public void test(){
        IHelloSingleton.get().sayHello();
        IHelloSingleton.get().sayHello2();
    }
}
