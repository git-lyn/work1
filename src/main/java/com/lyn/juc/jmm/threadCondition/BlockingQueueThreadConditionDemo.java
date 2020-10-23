package com.lyn.juc.jmm.threadCondition;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 采用阻塞队列的方式，实现线程之间的通信
 *
 *   volatile/CAS/
 *
 */
public class BlockingQueueThreadConditionDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));
        new Thread(() -> {
            System.out.println("生产者线程启动");
            myResource.product();
        },"生产者").start();

        new Thread(() -> {
            System.out.println("消费者启动");
            myResource.consumer();
        },"消费者").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myResource.stop();
        System.out.println("主线程停止数据的处理");
    }

}

class MyResource{
    // 创建一个flag标志位
    private volatile boolean flag = true;
    // 使用原子变量进行数据的加减操作,默认的结果为0
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getSimpleName() + " 当前的blockingQueue");
    }

    // 生产产品
    public void product(){
        String data = null;
        boolean result;
        while (flag){
            try {
                data = atomicInteger.incrementAndGet() + "";
                result = blockingQueue.offer(data,1, TimeUnit.SECONDS);
                if(result){
                    System.out.println(Thread.currentThread().getName() + "\t插入队列 " + data + "成功");
                }else{
                    System.out.println(Thread.currentThread().getName() + "\t插入队列 " + data + "失败");
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t大老板停止了工厂 表示flag=false");
    }
    // 消费产品
    public void consumer(){
        String result = null;
        while (flag){
            try {
                result = blockingQueue.poll(1,TimeUnit.SECONDS);
                if(result == null || result.equals("")){
                    // 停止在生产产品
                    flag = false;
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "\t消费队列" + result + "");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 停止数据的处理
    public void stop(){
        this.flag = false;
    }
}

class OurResource{
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public OurResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void product(){
        String data = null;
        boolean result;
        while (flag){
            try {
                data = atomicInteger.incrementAndGet() + "";
                result = blockingQueue.offer(data,2,TimeUnit.SECONDS);
                if(result){
                    System.out.println(Thread.currentThread().getName() + "\t队列填充 " + "成功");
                }else {
                    System.out.println(Thread.currentThread().getName() + "\t队列填充 " + "失败");
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t大老板停止了工厂");
    }

    public void consumer(){
        String result = null;
        while (flag){
            try {
                result = blockingQueue.poll(2,TimeUnit.SECONDS);
                if(result == null || result.equals("")){
                    System.out.println(Thread.currentThread().getName() + "\t超过2秒没有数据，消费推出");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "\t成功的消费队列数据");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 停止数据的处理
    public void stop(){
        this.flag = false;
    }

}