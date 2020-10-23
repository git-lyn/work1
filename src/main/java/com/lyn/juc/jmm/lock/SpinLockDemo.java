package com.lyn.juc.jmm.lock;

import com.lyn.juc.threadpool.TestHasBlockingQueueThreadPool;
import org.omg.CORBA.Current;
import sun.security.provider.ConfigFile;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 自旋锁(Spinlock)：
 *
 * 是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，
 * 这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU。
 *
 * 题目：实现一个自旋锁
 * 自旋锁好处：循环比较获取直到成功为止，没有类似wait的阻塞
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，B随后进行后发现
 * 当前线程持有锁，不是null，所以只能通过自悬等待，直到A释放后B随后抢到。
 *
 */

public class SpinLockDemo {
    public static void main(String[] args) {
//        SpinLock spinLock = new SpinLock();
//        new Thread(() -> {
//            spinLock.myLock();
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            spinLock.myUnlock();
//        },"AA").start();
//
//        new Thread(() -> {
//            spinLock.myLock();
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            spinLock.myUnlock();
//        },"BB").start();

        MySpinLock mySpinLock = new MySpinLock();
        new Thread(() -> {
            mySpinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
                mySpinLock.myUnlock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            mySpinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
                mySpinLock.myUnlock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();

    }

}

// 自定义一个循环锁
class SpinLock{
    // 创建原子引用变量
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null,thread)){
            System.out.println("没有获取锁");
        }
        System.out.println(Thread.currentThread().getName() + "\t lock");
    }

    // 解锁
    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "\t unlock **** ");
    }

}

// 创建自旋锁
class MySpinLock{
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\tcomming in ***");
        while (!atomicReference.compareAndSet(null,thread)){
//            System.out.println(Thread.currentThread().getName() + "\t循环等待####/*/####");
        }
    }
    public void myUnlock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\tout now...");
        atomicReference.compareAndSet(thread,null);
    }
}

/**
 * 创建对应的自旋锁
 */
class OurSpinLock{
    AtomicReference<Thread> reference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        while (!reference.compareAndSet(null, thread)) {
            System.out.println("正在获取锁");
        }
    }
    public void unLock(){
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread, null);
    }
}


class ASpinLock{
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        while (atomicReference.compareAndSet(null, thread)) {
            System.out.println("循环等待");
        }
    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
    }
}
