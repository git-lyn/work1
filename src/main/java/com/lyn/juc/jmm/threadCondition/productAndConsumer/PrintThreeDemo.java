package com.lyn.juc.jmm.threadCondition.productAndConsumer;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-13 16:51
 **/

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按照顺序调用，实现A->B->C三个线程启动，要求如下
 * AA打印5次，BB打印10次，CC打印15次,连续打印10轮
 * <p>
 * 线程操作资源类
 * 判断  工作   通知
 * 防止多线程之间的虚假唤醒
 */
public class PrintThreeDemo {
    public static void main(String[] args) {
//        semaphore();
//        lock();
        sync();
    }

    public static void semaphore() {
        SemephoreThread se = new SemephoreThread();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                se.A(i);
            }
        }, "线程11111").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                se.B(i);
            }
        }, "线程22222").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                se.C(i);
            }
        }, "线程33333").start();
    }

    public static void lock(){
        LockThread se = new LockThread();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                se.A(i);
            }
        }, "线程11111").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                se.B(i);
            }
        }, "线程22222").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                se.C(i);
            }
        }, "线程33333").start();
    }

    public static void sync(){
        SyncThread se = new SyncThread();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                se.A(i);
            }
        }, "线程11111").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                se.B(i);
            }
        }, "线程22222").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                se.C(i);
            }
        }, "线程33333").start();
    }
}

class SemephoreThread {
    private Semaphore semaphore1 = new Semaphore(1);
    private Semaphore semaphore2 = new Semaphore(0);
    private Semaphore semaphore3 = new Semaphore(0);

    public void A(int index) {
        try {
            semaphore1.acquire();
            System.out.println("第 " + index + " 轮 " + Thread.currentThread().getName() + " AAAAA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore2.release();
        }
    }

    public void B(int index) {
        try {
            semaphore2.acquire();
            System.out.println("第 " + index + " 轮 " + Thread.currentThread().getName() + " BBBBB");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore3.release();
        }
    }

    public void C(int index) {
        try {
            semaphore3.acquire();
            System.out.println("第 " + index + " 轮 " + Thread.currentThread().getName() + " CCCCC");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore1.release();
        }
    }

}

class LockThread {
    private Lock lock = new ReentrantLock();
    private int flag = 1;
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void A(int index) {
        try {
            lock.lock();
            while (flag != 1) {
                condition1.await();
            }
            for(int i = 1;i <= 5;i++) {
                System.out.println("第 " + index + " 轮 " + Thread.currentThread().getName() + " AAAAA");
            }
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void B(int index) {
        try {
            lock.lock();
            while (flag != 2) {
                condition2.await();
            }
            for(int i = 1;i <= 10;i++) {
                System.out.println("第 " + index + " 轮 " + Thread.currentThread().getName() + " BBBBB");
            }
            flag = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void C(int index) {
        try {
            lock.lock();
            while (flag != 3) {
                condition3.await();
            }
            for(int i = 1;i <= 15;i++) {
                System.out.println("第 " + index + " 轮 " + Thread.currentThread().getName() + " CCCCC");
            }
            flag = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

class SyncThread{
    private int flag = 1;
    public synchronized void A(int index){
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i = 1;i <= 5;i++) {
            System.out.println("第 " + index + " 轮 " + Thread.currentThread().getName() + " AAAAA");
        }
        flag = 2;
        this.notifyAll();
    }
    public synchronized void B(int index){
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i = 1;i <= 10;i++) {
            System.out.println("第 " + index + " 轮 " + Thread.currentThread().getName() + " BBBBB");
        }
        flag = 3;
        this.notifyAll();
    }
    public synchronized void C(int index){
        while (flag != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i = 1;i <= 15;i++) {
            System.out.println("第 " + index + " 轮 " + Thread.currentThread().getName() + " CCCCC");
        }
        flag = 1;
        this.notifyAll();
    }
}