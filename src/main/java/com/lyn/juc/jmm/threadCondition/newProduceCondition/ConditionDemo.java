package com.lyn.juc.jmm.threadCondition.newProduceCondition;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A、B、C三个线程交替打印
 *
 * @program: projects
 * @author: lyn
 * * @create: 2020-03-13 15:48
 **/
public class ConditionDemo {
    public static void main(String[] args) {

    }
}

class LockDemo {
    private Lock lock = new ReentrantLock();
    private int flag = 1;
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void aPrint() {
        try {
            lock.lock();
            while (flag != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "AAAAAA   " + i);
            }
            flag = 2;
            condition2.notify();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void bPrint() {
        try {
            lock.lock();
            while (flag != 2) {
                condition2.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "BBBBB   " + i);
            }
            flag = 3;
            condition3.notify();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void cPrint() {
        try {
            lock.lock();
            while (flag != 3) {
                condition3.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "BBBBB   " + i);
            }
            flag = 1;
            condition1.notify();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 *  采用Semaphone信号量的方式来实现三个线程的交替打印
 */
class SemaphoneDemo{
    private Semaphore semaphore = new Semaphore(1);
    private Semaphore semaphore2 = new Semaphore(0);
    private Semaphore semaphore3 = new Semaphore(0);

    public void aSemaphore() {
        try {
            semaphore.acquire();
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "AAAAAA   " + i);
            }
            semaphore2.release();
        } catch (InterruptedException e) {
        }
    }

    public void bSemaphore() {
        try {
            semaphore2.acquire();
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "BBBBB   " + i);
            }
            semaphore3.release();
        } catch (InterruptedException e) {
        }
    }


    public void cSemaphore() {
        try {
            semaphore3.acquire();
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "CCCCC   " + i);
            }
            semaphore.release();
        } catch (InterruptedException e) {
        }
    }

}