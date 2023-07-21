package com.threadlocal;

import com.reflect.proxy.two.MainActivityTwo;

public class TestMain {
    public static void main(String[] args){
        TestThreadLocal testThreadLocal = new TestThreadLocal();
        testThreadLocal.testThreadLocal();
        sort();
    }

    private static void sort(){
        int[] arr = {3, 2, 1, 4, 7};
        for(int i = 0; i < arr.length - 1; i++){
            for(int j=0; j<arr.length - i - 1; j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }

        for(int i =0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
