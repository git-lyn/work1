package com.lyn.juc.jmm.jvmXX;

/** 模拟经过15次垃圾回收，新生代到老年代
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-06 15:00
 **/
public class GeneralGC15 {
    public static void main(String[] args) {
        byte[] array1 = null;
        byte[] array2 = null;
        byte[] live = new byte[10 * 1024];
        for(int i = 1;i <= 9;i++) {
            array1 = new byte[32 * 1024 * 1024];
            array1 = null;
            array2 = new byte[32 * 1024 * 1024];
            array2 = null;
        }
    }
}
