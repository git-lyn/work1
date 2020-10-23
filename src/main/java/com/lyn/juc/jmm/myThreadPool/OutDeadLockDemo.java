package com.lyn.juc.jmm.myThreadPool;

import java.util.concurrent.TimeUnit;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-22 20:28
 *  模拟死锁的现象已经如何查看死锁的产生
 **/
public class OutDeadLockDemo {
    public static void main(String[] args) {
        String aa = "AA";
        String bb = "BB";
        new Thread(new OutDeadLock(aa,bb),"线程111").start();
        new Thread(new OutDeadLock(bb,aa),"线程222").start();
    }
}
class OutDeadLock implements Runnable{
    private String lockA;
    private String lockB;

    public OutDeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    public void deadLock(){
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t握有锁 " + lockA + "\t想要获取锁 " + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t握有锁 " + lockB + "\t想要获取锁 " + lockA);
            }
        }
    }

    @Override
    public void run() {
        deadLock();
    }
}
