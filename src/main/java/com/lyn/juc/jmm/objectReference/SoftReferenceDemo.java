package com.lyn.juc.jmm.objectReference;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-17 13:18
 **/

import java.lang.ref.SoftReference;

/**
 * 软引用：内存够用的时候就保留，不够就回收
 *   地主家有钱的时候，交租可以推迟一些，地主家也没有余粮时，要立刻交租。
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
        noGC();
        softReference_memory_notEnouth();

    }

    public static void softReference_memory_notEnouth() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();
        try{
            byte[] bytes = new byte[30 * 1024 * 1024];
        }catch (Throwable throwable) {
            throwable.getMessage();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void noGC() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }
}
