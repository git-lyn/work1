package com.lyn.juc.jmm.lock;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * 设计一个自旋锁
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-12 17:36
 **/
public class MySpinLocks {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 循环加锁
    public void lock(){
        Thread thread = Thread.currentThread();
        // 自旋
        while (!atomicReference.compareAndSet(null,thread)) {
            System.out.println(Thread.currentThread().getName() + " ...循环等待中... ");
            // 让出CPU的资源，进行优化的方式
            Thread.yield();
        }
    }

    // 解锁
    public void unlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
    }


    public static void main(String[] args) {
        MySpinLocks mySpinLocks = new MySpinLocks();
//        LockSupport.park();
        new Thread(() ->{
            mySpinLocks.lock();
            System.out.println(Thread.currentThread().getName() + " 获取自旋锁.... ");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }
            mySpinLocks.unlock();
            System.out.println(Thread.currentThread().getName() + " ####释放锁#####. ");
        }," AA线程：").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->{
            mySpinLocks.lock();
            System.out.println(Thread.currentThread().getName() + " 获取自旋锁.... ");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }
            mySpinLocks.unlock();
            System.out.println(Thread.currentThread().getName() + " ####释放锁#####. ");
        }," BB线程：").start();

    }
}
