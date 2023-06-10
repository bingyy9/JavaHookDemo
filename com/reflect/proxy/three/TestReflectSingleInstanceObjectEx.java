package com.reflect.proxy.three;

import com.reflect.proxy.common.HelloInvocationHandler;
import com.reflect.proxy.common.IHello;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestReflectSingleInstanceObjectEx {

    public static void main(String[] args){
        MainActivityThree mainActivityThree = new MainActivityThree();
        mainActivityThree.test();
        hookHelloService();
        mainActivityThree.test();
    }

    public static void hookHelloService(){
        try{
            Class<?> mainActivityThreeClazz = Class.forName("com.reflect.proxy.three.MainActivityThree");
            Field mHelloSingletonField = mainActivityThreeClazz.getDeclaredField("IHelloSingleton");
            mHelloSingletonField.setAccessible(true);
            //reflect MainActivityThree static field IHelloSingleton
            Object mHelloSingleton = mHelloSingletonField.get(null);

            Class<?> singletonClazz = Class.forName("com.reflect.proxy.common.Singleton");
            Field mInstanceField = singletonClazz.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            //reflect get MainActivityThree static field IHelloSingleton's instance object
            Object mHelloInstance = mInstanceField.get(mHelloSingleton);

            ClassLoader classLoader = mHelloInstance.getClass().getClassLoader();
            Class[] interfaces = mHelloInstance.getClass().getInterfaces();
            IHello helloProxy = (IHello) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("proxy before hello ... ");
                    Object result = method.invoke(mHelloInstance, args);
                    System.out.println("proxy after hello... ");
                    return result;
                }
            });

            //reflect set MainActivityThree static field IHelloSingleton's instance object to dynamic proxy object
            mInstanceField.set(mHelloSingleton, helloProxy);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

