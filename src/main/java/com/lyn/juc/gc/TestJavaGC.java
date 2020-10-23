package com.lyn.juc.gc;

import java.util.Random;

public class TestJavaGC {
    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println("######: "+ i);
//            GcRoot g1 = new GcRoot();
//            GcRoot g2 = new GcRoot();
//        }
        String str = "hello java";
        while (true){
            str += str + new Random().nextInt(500) + new Random().nextInt(10000);
        }
    }

    public static void main33(String[] args){
        long maxMemory = Runtime.getRuntime().maxMemory() ;//返回 Java 虚拟机试图使用的最大内存量。
        long totalMemory = Runtime.getRuntime().totalMemory() ;//返回 Java 虚拟机中的内存总量。
        System.out.println("MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");
    }

}

class GcRoot{
    private byte[] array = new byte[1 * 1024 * 1024 * 500];
}
