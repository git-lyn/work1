package com.lyn.juc.jmm.jvmXX;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-16 18:35
 **/
public class MyJVMGC {
    public static void main(String[] args) {
        // 打印GC信息
        byte[] bytes = new byte[20 * 1024 * 1024];

        System.out.println("Hello JVM GC");
    }
}
