package com.lyn.juc.atomic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestPrint {
    public static void main(String[] args) {
        final PrintDemo printDemo = new PrintDemo();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    printDemo.loopA(i);
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    printDemo.loopB(i);
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    printDemo.loopC(i);
                }
            }
        },"C").start();
    }
}

class PrintDemo {
    // 用于记录对应的线程
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(int loopNumber) {
        lock.lock();
        try{
            if (number != 1) {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + loopNumber);
            }
            number = 2;
            condition2.signal();
        }finally {
            lock.unlock();
        }

    }

    public void loopB(int loopNumber) {
        lock.lock();
        try{
            if (number != 2) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + loopNumber);
            }
            number = 3;
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void loopC(int loopNumber) {
        lock.lock();
        try{
            if (number != 3) {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + loopNumber);
            }
            number = 1;
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }
}
