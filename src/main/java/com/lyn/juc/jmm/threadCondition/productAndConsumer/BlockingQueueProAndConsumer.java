package com.lyn.juc.jmm.threadCondition.productAndConsumer;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  使用阻塞队列实现生产者消费者模式
 *
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-14 14:07
 **/
public class BlockingQueueProAndConsumer {
    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();
    }

    public static void test2() throws InterruptedException {
        BlockingModel blockingModel = new BlockingModel(new SynchronousQueue<>());
        new Thread(() ->{
            try {
                blockingModel.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者").start();

        new Thread(() -> {
            try {
                blockingModel.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费者线程").start();


        TimeUnit.SECONDS.sleep(10);

        blockingModel.stop();
    }

    public static void test1() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.offer(1, 2, TimeUnit.SECONDS);
        blockingQueue.offer(1, 2, TimeUnit.SECONDS);
        blockingQueue.offer(1, 2, TimeUnit.SECONDS);
        System.out.println("添加完成");
    }

}
// 使用阻塞队列实现生产者消费者模式
class BlockingModel{
    private volatile boolean flag = true;
    // 产品
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<Integer> blockingQueue = null;
    public BlockingModel(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    public void product() throws InterruptedException {
        int data = 0;
//        for(;;){
        while (flag){
            data = atomicInteger.incrementAndGet();
            blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " 生产产品编号: " + data);
            TimeUnit.MILLISECONDS.sleep(800);
        }
        System.out.println("工厂放假，停止生产产品");
    }

    public void consumer() throws InterruptedException {
        while (flag) {
            Integer res = blockingQueue.poll(2, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " 消费编号: " + res);
        }
    }

    public void stop(){
        flag = false;
    }

}


