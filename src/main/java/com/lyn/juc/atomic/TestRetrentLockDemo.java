package com.lyn.juc.atomic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class TestRetrentLockDemo {
    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        new Thread(lockDemo,"售票窗口1").start();
        new Thread(lockDemo,"售票窗口2").start();
        new Thread(lockDemo,"售票窗口3").start();
    }
}

class LockDemo implements Runnable{
    private int ticket = 100;
    private Lock lock = new ReentrantLock();
    public void run() {
        while (true) {
            lock.lock();
            try {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " 完成售票，余票为： " + --ticket);
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
