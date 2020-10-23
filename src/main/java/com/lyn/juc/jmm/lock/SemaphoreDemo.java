package com.lyn.juc.jmm.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore: 信号量
 * 六部车抢三个停车位
 *
 * 并发锁： synchronized   lock    semaphore
 *
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                // 获取停车位
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t获取停车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t停留3秒，离开停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放车位
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }


    public static void test(){
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() ->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 获取车位");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " 释放了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            });
        }

    }

}
