package com.lyn.juc.jmm.threadCondition;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  使用BlockingQueue实现生产者和消费者模式
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-13 19:13
 **/
public class BQThreadConditionDemo {
    public static void main(String[] args) {
        BQResource resource = new BQResource(new ArrayBlockingQueue<Integer>(5));
        new Thread(() -> {
            System.out.println("生产者线程启动");
            try {
                resource.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者").start();


        new Thread(() ->{
            System.out.println("消费者线程启动");
            try {
                resource.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费者").start();


        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程停产");
        resource.stop();

        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().availableProcessors());

    }

}

class BQResource{
    private volatile boolean flag = true;
    private AtomicInteger num = new AtomicInteger(0);
    private BlockingQueue<Integer> blockingQueue = null;

    public BQResource(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(this.getClass().getSimpleName() + " ***** ");
    }

    // 生产产品
    public void produce() throws InterruptedException {
        int data;
        boolean offer;
        while (flag) {
            data = num.incrementAndGet();
            offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + "   插入队列 " + data);
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("大老板停止了工厂生产产品");
    }

    // 消费产品
    public void consumer() throws InterruptedException {
        int data;
        while (flag) {
            data = blockingQueue.poll(2, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " 消费产品 " + data);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public void stop(){
        flag = false;
    }

}
