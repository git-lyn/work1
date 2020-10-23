package com.lyn.juc.jmm.threadCondition;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-22 16:28
 * 使用三种方式进行交替打印A、B、C三个线程
 **/
public class PrintABCThreeMethodDemo {
    public static void main(String[] args) {
        semaphorePrint();
    }

    public static void semaphorePrint() {
        // 采用信号量Semaphonre
        ABCSemephore semaphorePrint = new ABCSemephore();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                semaphorePrint.printAll(i);
            }
        }, "111").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                semaphorePrint.printAll(i);
            }
        }, "222").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                semaphorePrint.printAll(i);
            }
        }, "333").start();
    }


    public static void syncPrint() {
        // 第一种方式
        SynchronizedPrint synchronizedPrint = new SynchronizedPrint();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                synchronizedPrint.printA(i);
            }
        }, "线程111").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                synchronizedPrint.printB(i);
            }
        }, "线程222").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                synchronizedPrint.printC(i);
            }
        }, "线程333").start();

    }






}

// 使用同步方式进行打印
class SynchronizedPrint {
    // 标志位
    private int flag = 1;

    public synchronized void printA(int count) {
        // 判断
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 工作
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + "\tAAAAA" + "\t" + count);
        }
        flag = 2;
        // 唤醒
        this.notifyAll();
    }

    public synchronized void printB(int count) {
        // 判断
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 工作
        for (int i = 1; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "\tBBBBB" + "\t" + count);
        }
        flag = 3;
        // 唤醒
        this.notifyAll();
    }

    public synchronized void printC(int count) {
        // 判断
        while (flag != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 工作
        for (int i = 1; i < 15; i++) {
            System.out.println(Thread.currentThread().getName() + "\tCCCCC" + "\t" + count);
        }
        flag = 1;
        // 唤醒
        this.notifyAll();
    }

}

// 第二种方式：使用Semaphore信号量进行空
class SemaphoreABCPrint {
    // A开始的信号量，初始信号量数量为1
    Semaphore semaphore1 = new Semaphore(1);
    // B、C信号量，A完成后开始，初始信号量为0
    Semaphore semaphore2 = new Semaphore(0);
    Semaphore semaphore3 = new Semaphore(0);

    public void printA(int count) {
        try {
            // 线程1获取吸纳好执行，1信号量减1，当A为0时将无法继续获得该信号量
            semaphore1.acquire();
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\tAAA" + i + "\t" + count);
            }
            // 线程2释放信号量，B信号量加1（初始为0）,此时可以获取B信号量
            // 调用release，就加1
            semaphore2.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printB(int count) {
        try {
            // 线程1获取吸纳好执行，1信号量减1，当A为0时将无法继续获得该信号量
            semaphore2.acquire();
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\tBBB" + i + "\t" + count);
            }
            // 线程2释放信号量，B信号量加1（初始为0）,此时可以获取B信号量
            semaphore3.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printC(int count) {
        try {
            // 线程1获取吸纳好执行，1信号量减1，当A为0时将无法继续获得该信号量
            semaphore3.acquire();
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\tCCC" + i + "\t" + count);
            }
            // 线程2释放信号量，B信号量加1（初始为0）,此时可以获取B信号量
            semaphore1.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

// 打印 A、B、C 连续打印10遍
class ABCSemephore {
    Semaphore semaphore101 = new Semaphore(1);
    Semaphore semaphore102 = new Semaphore(0);
    Semaphore semaphore103 = new Semaphore(0);
    // 提供一个标志位，用于标识相应的三个线程
    int flag = 1;

    public void printAll(int temp) {
        if (flag == 1) {
            try {
                // 获取锁: 信号量减1
                semaphore101.acquire();
                System.out.println("第  " + temp + "  轮:  " + Thread.currentThread().getName() + " AAAAA");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                flag = 2;
                // 释放锁,  信号量加1
                semaphore102.release();
            }
        }

        //第二个线程: 用于打印BBBBB
        if (flag == 2) {
            try {
                semaphore102.acquire();
                System.out.println("第  " + temp + "  轮:  " + Thread.currentThread().getName() + " BBBBB");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                flag = 3;
                semaphore103.release();
            }
        }
        //第三个线程: 用于打印CCCCC
        if (flag == 3) {
            try {
                semaphore103.acquire();
                System.out.println("第  " + temp + "  轮:  " + Thread.currentThread().getName() + " CCCCC");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                flag = 1;
                semaphore101.release();
            }
        }
    }

    public synchronized void printSynAll(int temp) {
        while (flag == 1) {

        }
    }


}









