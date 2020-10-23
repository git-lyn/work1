package com.lyn.juc.jmm.threadCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-22 14:18
 **/
public class MyProductAndConsumerDemo {
    public static void main(String[] args) {
        MyProductAndConsumer productAndConsumer = new MyProductAndConsumer();
        // 创建生产者
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                productAndConsumer.product();
            }
        },"生产者").start();
        // 创建消费者
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                productAndConsumer.consumer();
            }
        },"消费者").start();
    }
}

// 线程操作资源类
class MyProductAndConsumer{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    // 生产产品
    public void product(){
        lock.lock();
        try{
            // 判断
            // 防止多线程的虚假唤醒
            while (number != 0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 工作
            number++;
            System.out.println(Thread.currentThread().getName() + "\t生产的产品编号：" + number);
            // 唤醒
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    // 消费产品
    public void consumer(){
        lock.lock();
        try{
            // 判断
            // 防止多线程的虚假唤醒
            while (number != 1){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 工作
//            number--;
            System.out.println(Thread.currentThread().getName() + "\t消费编号：" + number--);
            // 唤醒
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
