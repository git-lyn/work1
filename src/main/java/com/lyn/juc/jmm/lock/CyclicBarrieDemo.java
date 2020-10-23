package com.lyn.juc.jmm.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier： 循环栅栏
 *     收集七颗龙珠召唤神龙
 */
public class CyclicBarrieDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println(Thread.currentThread().getName() + "\t**** 开始召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println("收集第\t" + Thread.currentThread().getName() + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },i + "").start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test();

    }

    public static void test(){
        CyclicBarrier barrier = new CyclicBarrier(7,() ->{
            System.out.println("###   班长熄灯，最后一个走");
        });
        for (int i = 1; i < 8; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + " 学生离开了教室。。。");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },i + "").start();
        }

    }


}
