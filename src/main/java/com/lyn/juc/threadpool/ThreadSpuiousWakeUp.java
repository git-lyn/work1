package com.lyn.juc.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * 设置线程之间的虚假唤醒机制
 */
public class ThreadSpuiousWakeUp {
    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber();
        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                printNumber.increment(i);
            };
        },"生产者A").start();

        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                printNumber.increment(i);
            };
        },"生产者C").start();

        new Thread(() -> {
            for (int i = 1; i < 20; i++) {
                printNumber.decrement(i);
            }
        }
    ,"消费者B").start();

        new Thread(() -> {
            for (int i = 1; i < 20; i++) {
                printNumber.decrement(i);
            }
        }
                ,"消费者D").start();


    }

}

class PrintNumber{
    private int number = 0;
    // 自增
    public synchronized void increment(int countNumber){
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 判断
        while(number != 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 工作
        ++number;
        System.out.println(Thread.currentThread().getName() + "\t" + number + "\t" + countNumber);
        // 通知
        this.notifyAll();
    }
    // 自减
    public synchronized void decrement(int countNumber){
        // 判断
        while(number == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 工作
        --number;
        System.out.println(Thread.currentThread().getName() + "\t" + number + "\t" + countNumber);
        // 通知
        this.notifyAll();
    }
}
