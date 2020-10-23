package com.lyn.juc.jmm.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        // 抛出异常
        // blockQueueException(blockingQueue);
        // 返回false
        // blockQueueBoolean(blockingQueue);
        // 阻塞数据
//        block(blockingQueue);
        // 超时处理
        chashiDual(blockingQueue);

    }

    public static void chashiDual(BlockingQueue<String> blockingQueue) {
        try {
            System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 阻塞队列：put   take
    public static void block(BlockingQueue<String> blockingQueue) {
        try {
            blockingQueue.put("a");
            blockingQueue.put("b");
            blockingQueue.put("c");
//            blockingQueue.put("d");
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 返回boolean： offer  poll
    public static void blockQueueBoolean(BlockingQueue<String> blockingQueue) {
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    // 抛出异常的API： add remove
    public static void blockQueueException(BlockingQueue<String> blockingQueue) {
        // 抛出异常
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
//        blockingQueue.add("d");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // 获取队列头的数据信息
        System.out.println(blockingQueue.element());
    }
}
