package com.lyn.juc.jmm.threadCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式：
 *  使用Lock和Condition进行线程之间的通信处理。
 *
 *  线程操控资源类
 *  判断 工作 通知
 *  防止多线程之间的虚假唤醒
 *
 */
public class LockProductAndConsumerDemo {
    public static void main(String[] args) {
//        // 线程操作共享资源
//        Resources resources = new Resources();
//        new Thread(() -> {
//            for (int i = 1; i <= 10; i++) {
//                resources.increment();
//            }
//        },"生产者").start();
//        new Thread(() -> {
//            for (int i = 1; i <= 10; i++) {
//                resources.decrement(i);
//            }
//        },"消费者").start();

        synchronized (new LockProductAndConsumerDemo()){

        }
        new ReentrantLock();
    }
}

/**
 * 一个线程进行增加，一个线程进行减少
 */
//class Resources{
//    private int number = 0;
//    private Lock lock = new ReentrantLock();
//    private Condition condition = lock.newCondition();
//    // 生产产品
//    public void increment(){
//        lock.lock();
//        try{
//            // 判断
//            while (number != 0){
//                try {
//                    condition.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            // 工作
//            number++;
//            System.out.println(Thread.currentThread().getName() + "\t生产产品编号：" + number);
//            // 通知
//            condition.signalAll();
//        }finally {
//            lock.unlock();
//        }
//    }
//    // 消费产品
//    public void decrement(int count){
//        lock.lock();
//        try{
//            // 判断
//            while (number == 0){
//                try {
//                    condition.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            // 工作
//            number--;
//            System.out.println(Thread.currentThread().getName() + "\t消费产品编号：" + number);
//            System.out.println("******第 " + count + " 轮********");
//            // 通知
//            condition.signalAll();
//        }finally {
//            lock.unlock();
//        }
//    }
//}
