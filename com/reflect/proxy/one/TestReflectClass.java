package com.reflect.proxy.one;

import java.lang.reflect.Field;

//this demo is to
// 1. reflect mainActivity object's int value field and get value
public class TestReflectClass {

    public static void main(String[] args){
        MainActivityOne mainActivityOne = new MainActivityOne();
        mainActivityOne.setValue(5);

        try{
//            Class<?> mainActivityObj = MainActivityOne.class;
            Class<?> mainActivityClazz = Class.forName("com.reflect.proxy.one.MainActivityOne");
//            Class<?> mainActivityObj = mainActivityOne.getClass();
            Field intValueField = mainActivityClazz.getDeclaredField("mValue");
            intValueField.setAccessible(true);
            int value = intValueField.getInt(mainActivityOne);
            System.out.println(" mainActivityOne: intAField: " + intValueField + " value: " + value);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

