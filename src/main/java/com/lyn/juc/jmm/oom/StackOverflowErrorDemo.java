package com.lyn.juc.jmm.oom;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-07 16:47
 **/
public class StackOverflowErrorDemo {
    public static byte[] aa = new byte[20 * 1024 * 1024];
    public static void main(String[] args) {
        err();
    }
    public static void err(){
        byte[] bytes = new byte[20 * 1024 * 1024];
    }
}
