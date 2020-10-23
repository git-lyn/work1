package com.lyn.juc.jmm.volatiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JMM Java  Memory Model
 * java内存模型：
 * 1、可见性
 * 2、原子性
 * 3、顺序性
 * 、
 * 测试volatile内存可见性
 *
 * 2 验证volatile不保证原子性
 * 2.1 原子性指的是什么意思？
 *     不可见分割，完整性，也即某个线程正在做某个具体业务时，中间不可以北加塞或者被分割。
 *     需要整体完整要么同时成功，要么同时失败。
 *
 *
 * 2.4 如何解决原子性？
 *    synchronized
 *    AtomicInteger
 *
 *
 */
public class TestVolatiles {
    public static void main(String[] args) {
        VolaDemo volaDemo = new VolaDemo();
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + "\t" + "当前线性休息3秒");
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            volaDemo.add();
//            System.out.println("修改后的值为： " + volaDemo.number);
//        },"AAA").start();
//
//        while ( volaDemo.number == 0){
//
//        }

        CountDownLatch countDownLatch = new CountDownLatch(20);
        // 验证Volatile不具有原子性
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    volaDemo.addone();
                    volaDemo.atomic();
                }
                countDownLatch.countDown();
            },i + "").start();
        }
        // 需要等待上面20个线程全部计算完成后
//        while (Thread.activeCount() > 2){
//            Thread.yield();
//        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程获取的数据： " + volaDemo.number + "\tatomicInteger: " + volaDemo.atomicInteger);
    }
}

class VolaDemo{
      volatile int number = 0;
      // 使用AtomicInteger保证原子性操作
    public AtomicInteger atomicInteger = new AtomicInteger(0);
    public void atomic(){
        atomicInteger.getAndIncrement();
    }

     public void add(){
         number = 30;
     }
    public void addone(){
        number++;
    }


}
