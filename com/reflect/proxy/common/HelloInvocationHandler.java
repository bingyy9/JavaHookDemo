package com.reflect.proxy.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloInvocationHandler implements InvocationHandler {
   private Object target;
   public HelloInvocationHandler(Object target) {
      this.target = target;
   }
   public Object invoke(Object proxy, Method method, Object[] args)
           throws Throwable {
      System.out.println("proxy before hello ... ");
      Object result = method.invoke(this.target, args);
      System.out.println("proxy after hello... ");
      return result;
   }
}
