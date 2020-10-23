package com.lyn.juc.jmm.threadCondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-22 16:05
 *  通过线程池的方式按照顺序打印三个线程的数据
 **/
public class ThreadPoolPrintDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\tAAAAAA" + "\t第" + i + "轮");
            }
        },"A"));
        executorService.execute(new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\tBBBBBB" + "\t第" + i + "轮");
            }
        },"B"));
        executorService.execute(new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\tCCCCCC" + "\t第" + i + "轮");
            }
        },"C"));
    }
}
