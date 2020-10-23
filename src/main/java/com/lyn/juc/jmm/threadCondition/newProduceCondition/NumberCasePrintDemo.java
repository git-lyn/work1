package com.lyn.juc.jmm.threadCondition.newProduceCondition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印字母和数字:
 * 两个线程打印； 1 2 -> A 3 4 -> B  5 6 -> C
 *
 * @program: projects
 * @author: lyn
 * * @create: 2020-03-13 16:28
 **/
public class NumberCasePrintDemo {
    public static void main(String[] args) {
        lockNumber();
    }

    public static void lockNumber() {
        LockPrintNC lockPrintNC = new LockPrintNC();
        new Thread(() ->{
            for(int i = 1;i < 53;i+=2) {
                lockPrintNC.number(i);
            }
        },"数字打印").start();

        new Thread(() ->{
            for(int i = 65;i <= 92;i++) {
                lockPrintNC.cases(i);
            }
        },"字母打印").start();
    }
}

class LockPrintNC {

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private int flag = 1;

    public void number(int num) {
        try {
            lock.lock();
            while (flag != 1) {
                condition1.await();
            }
            for (int i = num; i <= num + 1; i++) {
                System.out.println(Thread.currentThread().getName() + "  " + i);
            }
            TimeUnit.MILLISECONDS.sleep(100);
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void cases(int num) {
        try {
            lock.lock();
            while (flag != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "  " + (char)num);
            TimeUnit.MILLISECONDS.sleep(10);
            flag = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


