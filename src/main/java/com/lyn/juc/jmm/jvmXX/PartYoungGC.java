package com.lyn.juc.jmm.jvmXX;

/** 模拟YoungGC后存活的对象，survivor放不下，部分对象进入老年代
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-06 16:03
 **/
public class PartYoungGC {
    public static void main(String[] args) {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = null;
        byte[] small = new byte[128 * 1024];
        byte[] array3 = new byte[1 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];

    }
}
