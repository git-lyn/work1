package com.lyn.juc.jmm.threadCondition;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-22 15:19
 **/
public class MyBlockingQueueThreadConditionDemo {
    public static void main(String[] args) {
        MyBlockingQueueCondition queueCondition = new MyBlockingQueueCondition(new ArrayBlockingQueue<String>(5));
        new Thread(() -> {
            queueCondition.product();
        },"生产者").start();
        new Thread(() -> {
            queueCondition.consumer();
        },"消费者").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queueCondition.stop();
    }
}
// 阻塞队列版本的生产者消费者模式
class MyBlockingQueueCondition{
    // 创建一个标志位
    private volatile boolean flag = true;
    // 创建产品
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public MyBlockingQueueCondition(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println("当前的阻塞队列位：" + blockingQueue.getClass().getSimpleName());
    }

    // 生产产品
    public void product(){
        String data = null;
        boolean result;
        while (flag){
            data = atomicInteger.getAndIncrement() + "";
            try {
                result = blockingQueue.offer(data,2, TimeUnit.SECONDS);
                if(result){
                    System.out.println(Thread.currentThread().getName() + "\t队列添加：" + data);
                }else{
                    return;
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t大boss停止了工厂");
    }
    // 消费产品
    public void consumer(){
        String result = null;
        while (flag){
            try {
                result = blockingQueue.poll(2, TimeUnit.SECONDS);
                if(result == null || result.equals("")){
                    System.out.println("队列为空");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "\t消费队列数据：" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // 停止数据的处理
    public void stop(){
        flag = false;
    }
}
