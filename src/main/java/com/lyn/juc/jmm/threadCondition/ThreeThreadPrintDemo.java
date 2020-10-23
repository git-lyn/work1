package com.lyn.juc.jmm.threadCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按照顺序调用，实现A->B->C三个线程启动，要求如下
 * AA打印5次，BB打印10次，CC打印15次,连续打印10轮
 *
 *    线程操作资源类
 *    判断  工作   通知
 *    防止多线程之间的虚假唤醒
 */
public class ThreeThreadPrintDemo {
    public static void main(String[] args) {
        MyThree myThree = new MyThree();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
//                myThree.loopAA(i);
                myThree.printAll();
            }
        },"线程1111").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
//                myThree.loopBB(i);
                myThree.printAll();
            }
        },"线程2222").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
//                myThree.loopCC(i);
                myThree.printAll();
            }
        },"线程3333").start();
    }
}
// 三个线程交替打印
class MyThree{
    private int number = 1; // 判断当前的线程
    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    // 打印AA
    public void loopAA(int count){
        lock.lock();
        try{
            // 判断
            while (number != 1){
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 工作
            // AA 打印5遍
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "  " +  i + "\tAA" + " 第" + count + "遍");
            }
            // 通知
            number = 2;
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }
    // 打印BB 10 遍
    public void loopBB(int count){
        lock.lock();
        try{
            // 判断
            while (number != 2){
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "  " + i +  "\tBB"+ " 第" + count + "遍");
            }
            // 通知
            number = 3;
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }
    // 打印CC 15遍
    public void loopCC(int count){
        lock.lock();
        try{
            // 判断
            while (number != 3){
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 工作
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "  " + i +  "\tCC"+ " 第" + count + "遍");
            }
            // 通知
            number = 1;
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 答应所有的数据
     */
    public void printAll(){
        lock.lock();
        try{
            // 判断
            while (number != 1){
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 工作
            // AA 打印5遍
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "  " +  i + "\tAA");
            }
            // 通知
            number = 2;
            condition2.signal();
        }finally {
            lock.unlock();
        }
        lock.lock();
        try{
            // 判断
            while (number != 2){
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "  " + i +  "\tBB");
            }
            // 通知
            number = 3;
            condition3.signal();
        }finally {
            lock.unlock();
        }

        lock.lock();
        try{
            // 判断
            while (number != 3){
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 工作
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "  " + i +  "\tCC");
            }
            // 通知
            number = 1;
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }

}

