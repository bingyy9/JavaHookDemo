package com.reflection;

import com.reflect.proxy.common.HelloInvocationHandler;
import com.reflect.proxy.common.IHello;
import com.reflect.proxy.two.MainActivityTwo;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class TestReflectObject {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Person person1 = new Person(1);
        Person person2 = new Person(2);

        Class<?> personClazz = Class.forName("com.reflection.Person");

        Field ageField = personClazz.getDeclaredField("age");
        int age1 = ageField.getInt(person1);
        int age2 = ageField.getInt(person2);
        System.out.println("age1:" + age1);
        System.out.println("age2:" + age2);
    }

}

