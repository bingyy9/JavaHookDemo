package com.reflect.proxy.common;

public class HelloModel {
    private static IHello mInstance;
    public static IHello getInstance(){
        if(mInstance == null){
            mInstance = new HelloService();
        }
        return mInstance;
    }

    public IHello getInstance2(){
        if(mInstance == null){
            mInstance = new HelloService();
        }
        return mInstance;
    }
}
