package com.lyn.juc.jmm.myThreadPool;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: projects
 * @description: 死锁
 * @author: lyn
 * * @create: 2019-04-16 14:40
 * 死锁是指两个或者两个以上的进程在执行过程钟，
 * 因争夺资源而造成的一种互相等待的现象，
 * 若无外力干涉那他们都将无法进行下去
 **/
public class DeadLockDemo {
    public static void main(String[] args) {
        String a = "lockA";
        String b = "lockB";
        new Thread(new MyDeadLock(a,b),"线程11111").start();
        new Thread(new MyDeadLock(b,a),"线程22222").start();
    }
}

// 死锁线程
class MyDeadLock implements Runnable {

    private String lockA;
    private String lockB;

    public MyDeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t自己持有: " + lockA + "\t尝试获取 " + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有: " + lockB + "\t尝试获取 " + lockA);
            }
        }
    }
}
