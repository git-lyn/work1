package com.lyn.juc.jmm.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步阻塞队列：SynchronousQueue
 *   不存储元素的队列，生产一个消费一个。
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\tput 1");
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName() + "\tput 2");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName() + "\tput 3");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
