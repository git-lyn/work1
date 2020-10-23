package com.lyn.juc.threadpool;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有两个线程：
 *   一个打印 1-52， 一个线程打印A-Z
 *     1 2 A 3 4 B 5 6 C
 */
public class PrintNumberAndUpperCase {
    public static void main(String[] args) {
        NumberAndUpperCase numberAndUpperCase = new NumberAndUpperCase();
        new Thread(() -> {
            for (int i = 1; i <= 52; i++) {
                numberAndUpperCase.number(i);
            }
        },"生产者1").start();
        new Thread(() -> {
            for (int i = 65; i <= 90; i++) {
                numberAndUpperCase.upperCase(i);
            }
        },"生产者2").start();

//        LockNumberAndUpper lockNumberAndUpper = new LockNumberAndUpper();
//
//        new Thread(() -> {
//            for (int i = 1; i <= 52; i++) {
//                lockNumberAndUpper.number(i);
//            }
//        },"AA").start();
//        new Thread(() -> {
//            for (int i = 65; i <= 90; i++) {
//                lockNumberAndUpper.upperCase(i);
//            }
//        },"BB").start();

}
}

class  NumberAndUpperCase{

    private AtomicInteger number = new AtomicInteger(0);
    private AtomicInteger upperCase = new AtomicInteger(0);

    // 打印数字
    public synchronized void number(int num){
        // 判断
        while(number.get() == 2){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            number.getAndSet(0);
        }
        // 工作
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        number.getAndIncrement();
        // 通知
        this.notifyAll();
    }
    // 打印字母
    public synchronized void upperCase(int num){
        // 判断
        while(upperCase.get() == 1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            upperCase.getAndSet(0);
        }
        // 工作
        System.out.println(Thread.currentThread().getName() + "\t" + (char)num);
        upperCase.getAndIncrement();
        // 通知
        this.notifyAll();
    }
}

class LockNumberAndUpper{
    private int number = 0;
    private int upper = 0;
    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
//    Condition condition2 =lock.newCondition();

    // 打印数字
    public void number(int countNumber){
        lock.lock();
        try{
          // 判断
          while (number == 2){
              try {
                  condition1.await();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              number = 0;
          }
          // 工作
            System.out.println(Thread.currentThread().getName() + "\t" + countNumber);
          // 通知
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }
    // 打印字母
    public void upperCase(int countNumber){
        lock.lock();
        try{
            // 判断
            while(upper == 1){
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                upper = 0;
            }
            // 工作
            System.out.println(Thread.currentThread().getName() + "\t" + countNumber);
            // 通知
             condition1.signal();
        }finally {
            lock.unlock();
        }
    }
}
