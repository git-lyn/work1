package com.lyn.juc.jmm.threadCondition.productAndConsumer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程打印； 1 2 -> A 3 4 -> B  5 6 -> C
 *
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-13 18:43
 **/
public class NumberCaseThread {
    public static void main(String[] args) {
        System.out.println((char) 97);
//        sync();
        lock();
    }

    public static void lock() {
        LockPrint syncPrint = new LockPrint();
        new Thread(() -> {
            for (int i = 1; i <= 54; i++) {
                syncPrint.number(i);
            }
        }, "11111").start();

        new Thread(() -> {
            for (int i = 65; i <= 92;i++) {
                syncPrint.cases(i);
            }
        }, "22222").start();
    }

    public static void sync() {
        SyncPrint syncPrint = new SyncPrint();
        new Thread(() -> {
            for (int i = 1; i <= 52; i++) {
                syncPrint.number(i);
            }
        }, "11111").start();

        new Thread(() -> {
            for (int i = 65; i <= 90; i++) {
                syncPrint.cases(i);
            }
        }, "22222").start();
    }
}

class SyncPrint {
    private AtomicInteger atomicInteger = new AtomicInteger();
    private int flag = 1;
    private int number = 0;
    private int ca = 0;

    public synchronized void number(int index) {
//        if (flag == 1) {
        while (number == 2) {
            number = 0;
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 数字: " + index);
        number++;
        this.notify();
//        }

    }

    public synchronized void cases(int index) {
        while (ca == 1) {
            ca = 0;
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 字母: " + (char) index);
        ca++;
        this.notify();

    }

    public synchronized void A(int index) {
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = index; i <= index + 1; i++) {
            System.out.println(Thread.currentThread().getName() + " 数字: " + i);
        }
        flag = 2;
        this.notifyAll();
    }

    public synchronized void B(int index) {
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 字母: " + (char) index);
        flag = 1;
        this.notifyAll();
    }

    private int num = 0;
    private int flag2 = 0;

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
        while (flag2 != 0) {
            flag2 = 0;
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " " + (char) temp);
        flag2++;
        this.notify();
    }

}

class LockPrint {
    private int num = 0;
    private int ca = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private int flag = 0;

    public void number(int index) {
        try {
            lock.lock();
            while (flag == 2) {
                flag = 0;
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + " 数字: " + index);
            flag++;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void cases(int index) {
        try {
            lock.lock();
            while (flag != 0) {
                flag = 0;
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + " 字母: " + (char)index);
            flag++;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
