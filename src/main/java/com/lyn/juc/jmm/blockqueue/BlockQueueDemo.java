package com.lyn.juc.jmm.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-13 14:13
 **/
public class BlockQueueDemo {
    public static void main(String[] args) {
//        exception();
        synchronousBQ();
    }

    public static void exception() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        // 抛出异常的三个方法
        // 添加元素
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println("#########");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue);

    }

    public static void synchronousBQ() {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 添加元素a");
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName() + " 添加元素b");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName() + " 添加元素c");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAAA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " 获取元素: " + blockingQueue.take());
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " 获取元素: " + blockingQueue.take());
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " 获取元素: " + blockingQueue.take());
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBBB").start();
    }

}
