package com.lyn.juc.jmm.jvmXX;


/** 仿造每秒500访问量的BI系统: 每秒100kb 50M每秒  每秒10万并发访问的BI系统
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-06 18:50
 **/
public class BIdemo {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30000);
        while (true) {
            loadData();
        }
    }
    public static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data = null;
        Thread.sleep(1000);
    }
}
