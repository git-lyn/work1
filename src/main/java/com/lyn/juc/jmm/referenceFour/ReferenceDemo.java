package com.lyn.juc.jmm.referenceFour;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 *  强、软、若、虚四大引用的使用
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-19 14:26
 **/
public class ReferenceDemo {
    public static void main(String[] args) {
        softReference();
    }

    public static void softReference(){
        //
        String str = "hell";
        SoftReference<String> softReference = new SoftReference<String>(str);
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        System.out.println(softReference.get());
        System.gc();
        System.out.println("==============");

        try {
            int[] by = new int[50 * 1024 * 1024];
        } catch (Error e) {
            System.out.println("报错了");
            System.out.println(softReference.get());
        }

    }

}
