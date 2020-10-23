package com.lyn.juc.atomic;

import scala.xml.dtd.impl.Base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用线程通信的方式，交替打印A、B、C打印10遍
 */

public class TestAlternateDemo {
    public static void main(String[] args) {
        final AlternateDemo alternateDemo = new AlternateDemo();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    alternateDemo.loopA(i);
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    alternateDemo.loopB(i);
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    alternateDemo.loopC(i);
                }
            }
        },"C").start();
    }
}


class AlternateDemo{
    // 标记当前线程
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    // 打印的轮数
    public void loopA(int countLoop){
        lock.lock();
        try{
            if(number != 1){
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 执行对应的打印认为
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + countLoop);
            }
            number = 2;
            // 唤醒其它的线程
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void loopB(int countLoop){
        lock.lock();
        try{
            if(number != 2){
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 执行对应的打印认为
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + countLoop);
            }
            number = 3;
            // 唤醒其它的线程
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void loopC(int countLoop){
        lock.lock();
        try{
            if(number != 3){
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 执行对应的打印认为
            for (int i = 1; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + countLoop);
            }
            System.out.println("###########################");
            number = 1;
            // 唤醒其它的线程
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }

}