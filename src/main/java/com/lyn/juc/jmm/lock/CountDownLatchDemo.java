package com.lyn.juc.jmm.lock;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch: 只有当其它线程处理完数据后，当前线程才进行处理。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 被灭国了");
                // 计数器减少1
                countDownLatch.countDown();
            }, CountryEnum.getCountry(i).getMessage()).start();
        }
        // 挂起当前线程
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t****** 大秦帝国统一全国");
        System.out.println(CountryEnum.ONE.getMessage());



        //test1();
    }

    public static void test1() {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "  走出了教室");
                latch.countDown();
            }, String.valueOf(i)).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 班长走出了教室");
    }

}
