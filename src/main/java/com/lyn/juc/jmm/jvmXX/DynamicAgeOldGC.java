package com.lyn.juc.jmm.jvmXX;

/** 动态对象年龄判断: survivor对象超过一般，进入老年代
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-06 14:35
 **/
public class DynamicAgeOldGC {
    public static void main(String[] args) {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        byte[] small = new byte[128 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024]; // 1

        array2 = new byte[2 * 1024 * 1024];
        array2 = new byte[2 * 1024 * 1024];
        array2 = new byte[128 * 1024];
        array2 = null;

        byte[] array3 = new byte[2 * 1024 * 1024]; // 2

    }
}
