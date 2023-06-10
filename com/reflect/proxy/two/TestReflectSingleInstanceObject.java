package com.reflect.proxy.two;

import com.reflect.proxy.common.HelloInvocationHandler;
import com.reflect.proxy.common.IHello;
import com.reflect.proxy.one.MainActivityOne;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class TestReflectSingleInstanceObject {

    public static void main(String[] args){
        MainActivityTwo mainActivityTwo = new MainActivityTwo();
        mainActivityTwo.test();
        hookHelloService();
        mainActivityTwo.test();
    }

    public static void hookHelloService(){
        try{
            Class<?> helloModelClazz = Class.forName("com.reflect.proxy.common.HelloModel");
            Field mHelloInstanceField = helloModelClazz.getDeclaredField("mInstance");
            mHelloInstanceField.setAccessible(true);
            //reflect static object field
            IHello mHelloInstance = (IHello) mHelloInstanceField.get(null);

            //2. dynamic proxy helloService interfaces
            HelloInvocationHandler handler = new HelloInvocationHandler(mHelloInstance);
            //3. get ClassLoader
            ClassLoader classLoader = mHelloInstance.getClass().getClassLoader();
            //4. get Interfaces
            Class[] interfaces = mHelloInstance.getClass().getInterfaces();
            //5. create proxy class object
            IHello hello = (IHello) Proxy.newProxyInstance(classLoader, interfaces, handler);

            //reflect set static object field
            mHelloInstanceField.set(null, hello);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

