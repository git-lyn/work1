package com.lyn.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile： 保证内存可见性
 *
 * 不足：
 *  1、不具有互斥性
 *  2. 没有原子性操作
 *
 *
 *  原子变量：jdk1.5后Java.util.concurrent.atomic 包下提供了常见的
 *       原子变量：
 *       1. volatile保证内存可见性
 *       2. CAS(Compare-And-Swap)算法保证数据的原子性
 *       `  CAS算法是硬件对于并发操作共享数据的支持
 *          CAS包含了三个操作数：
 *             内存值 V
 *             预估值 A
 *             更新值 B
 *             当且仅当 V == A 时，v = B，否则，将 不做任何操作
 *
 */

public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }
    }
}

class AtomicDemo implements Runnable{
//    private int num = 0;
    private AtomicInteger num = new AtomicInteger(0);
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": " + getNum());
    }
    public int getNum(){
//        return num++;
        return num.getAndIncrement();
    }
}
