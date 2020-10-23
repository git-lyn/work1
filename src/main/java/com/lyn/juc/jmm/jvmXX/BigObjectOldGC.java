package com.lyn.juc.jmm.jvmXX;

/** 大对象直接移动到老年代: -XX:PretenureSizeThreshold=3m
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-06 16:29
 **/
public class BigObjectOldGC {
    public static void main(String[] args) {
        byte[] array1 = new byte[4 * 1024 * 1024];
        array1 = null;
        byte[] array2 = new byte[2 * 1024 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];
        byte[] array4 = new byte[2 * 1024 * 1024];
        byte[] array5 = new byte[128 * 1024];
        byte[] array6 = new byte[2 * 1024 * 1024];

    }
}
