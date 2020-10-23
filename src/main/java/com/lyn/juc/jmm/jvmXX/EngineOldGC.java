package com.lyn.juc.jmm.jvmXX;

/** 每日百亿数据量的实时分析引擎，频繁产生FullGC
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-06 19:56
 **/
public class EngineOldGC {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30000);
        while (true) {
            data();
        }
    }
    public static void data() throws InterruptedException {
        byte[] array1 = null;
        for (int i = 0; i < 4; i++) {
            array1 = new byte[10 * 1024 * 1024];
        }
        array1 = null;
        byte[] array2 = new byte[10 * 1024 * 1024];
        byte[] array3 = new byte[10 * 1024 * 1024];
        byte[] array4 = new byte[10 * 1024 * 1024];
        array4 = new byte[10 * 1024 * 1024];
        Thread.sleep(1000);
    }
}
