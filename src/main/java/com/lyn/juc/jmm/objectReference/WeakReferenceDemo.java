package com.lyn.juc.jmm.objectReference;

import java.lang.ref.WeakReference;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-17 13:39
 *  弱引用： 不管内存够不够用，只要有GC就一定被回收
 **/
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<Object>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        System.out.println("&&&&&&&&&&&&");
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
