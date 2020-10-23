package com.lyn.juc.jmm.jvmXX;

/**
 * 模拟频繁产生youngGC
 *
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-06 13:52
 **/
public class YoungGCDemo {
    public static void main(String[] args) {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        byte[] small = new byte[128 * 1024]; // 默认会创建512kb的未知对象
        array1 = null;
        byte[] array2 = new byte[2 * 1024 * 1024];
//        System.out.println("DFDFF");
    }
}
