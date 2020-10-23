package com.lyn.juc.threadpool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印 1 2 A 3 4 B
 *
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-23 16:13
 **/
public class NumberAndCasePrintDemo {
    public static void main(String[] args) {
//        System.out.println((char)90);
        print1();
//        lockPrint();
    }

    public static void lockPrint() {
        NumDemo numDemo = new NumDemo();

        new Thread(() -> {
            for (int i = 1; i <= 52; i++) {
                numDemo.lockNumber(i);
            }
        }, "1111： ").start();

        new Thread(() -> {
            for (int i = 65; i <= 90; i++) {
                numDemo.lockCase(i);
            }
        }, "字母： ").start();
    }

    public static void print1() {
        NumDemo numDemo = new NumDemo();

        new Thread(() -> {
            for (int i = 1; i <= 52; i++) {
                numDemo.synNumber(i);
            }
        }, "1111： ").start();

        new Thread(() -> {
            for (int i = 65; i <= 90; i++) {
                numDemo.synCase(i);
            }
        }, "字母： ").start();
    }
}

class NumDemo {
    private AtomicInteger nu = new AtomicInteger(0);
    private int num = 0;
    private int flag = 0;

    public synchronized void synNumber(int temp) {
        while (num == 2) {
            num = 0;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " " + temp);
//        flag++;
        // 进行++ 操作
//        nu.getAndIncrement();
        num++;
        this.notify();
    }

    public synchronized void synCase(int temp) {
        while (flag != 0) {
            flag = 0;
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " " + (char) temp);
        flag++;
        this.notify();
    }

    private Lock lock = new ReentrantLock();
    private Condition condition101 = lock.newCondition();
    private Condition condition102 = lock.newCondition();

    public void lockNumber(int temp) {
        try {
            lock.lock();
            while (num == 2) {
                num = 0;
                condition101.await();
            }
            System.out.println(Thread.currentThread().getName() + " " + temp);
            num++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            condition102.signal();
            lock.unlock();
        }
    }

    public void lockCase(int temp) {
        try {
            lock.lock();
            while (flag == 1) {
                flag = 0;
                condition102.await();
            }
            System.out.println(Thread.currentThread().getName() + " " + (char) temp);
            flag++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            condition101.signal();
            lock.unlock();
        }
    }

    private Semaphore semaphore101 = new Semaphore(1);
    private Semaphore semaphore102 = new Semaphore(0);

    public void semaphoreNumber(int temp) {
        try {
            semaphore101.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore101.release();
    }


}
