package com.lyn.juc.jmm.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Lock Synchronized 都是非公平的可重入锁
 *
 * 可重入锁（也叫做递归锁）
 * 指的时同一线程函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 * 也即是说，线程可以进行任何一个它已经拥有的锁所同步这的代码块。
 *
 */

public class ReentrantLockDemo {
    public static void main(String[] args) {

        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        readLock.lock();
        readLock.unlock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        writeLock.unlock();

        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendMsg();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            phone.msg();
        },"T1").start();
        System.out.println("#$#########");
        new Thread(() -> {
//            phone.sendMsg();
            phone.sendEmail();
//            phone.email();
        },"T2").start();
    }
}

class Phone{
    Lock lock = new ReentrantLock();
    public synchronized void sendMsg(){
        System.out.println(Thread.currentThread().getName() + "\t" + "发短信了");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t 发送Email了");
    }

    public void msg(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t" + "发短信了");
            email();
        }finally {
            lock.unlock();
        }
    }

    public void email() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 发送Email了");
        }finally {
          lock.unlock();
        }

    }

}
