package com.reflect.proxy.one;

import com.reflect.proxy.common.HelloInvocationHandler;
import com.reflect.proxy.common.IHello;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

//this demo is to
// 1. reflect mainActivity object's helloService object.
// 2. dynamic proxy the reflected helloService object, inject yourSelf logic while invoke the method
public class TestReflectClassObject {

    public static void main(String[] args){
        MainActivityOne mainActivityOne = new MainActivityOne();
        hookHelloService(mainActivityOne);
        mainActivityOne.test();
    }

    public static void hookHelloService(MainActivityOne mainActivityOne){
        try{
            mainActivityOne.test();

//            Class<?> mainActivityObj = mainActivityOne.getClass();
            Class<?> mainActivityClazz = Class.forName("com.reflect.proxy.one.MainActivityOne");

            // get mainActivity helloService object filed
            Field helloServiceField = mainActivityClazz.getDeclaredField("helloService");
            helloServiceField.setAccessible(true);
            // get mainActivity helloService object
            Object helloService = helloServiceField.get(mainActivityOne);  //获取testHook对象的helloServiceField的值。

            //2. dynamic proxy helloService interfaces
            HelloInvocationHandler handler = new HelloInvocationHandler(helloService);
            //3. get ClassLoader
            ClassLoader classLoader = helloService.getClass().getClassLoader();
            //4. get Interfaces
            Class[] interfaces = helloService.getClass().getInterfaces();
            //5. create proxy class object
            IHello hello = (IHello) Proxy.newProxyInstance(classLoader, interfaces, handler);

            System.out.println(" hello: " + helloService.hashCode() + " hell proxy: " + hello.hashCode());
            //inject proxy class object to mainActivity object's helloService field
            helloServiceField.set(mainActivityOne, hello);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

