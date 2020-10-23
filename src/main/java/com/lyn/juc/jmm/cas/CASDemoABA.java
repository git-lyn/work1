package com.lyn.juc.jmm.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-19 19:01
 *
 * 使用原子运用模拟ABA的问题的产生，同时使用原子版本引用来解决ＡＢＡ问题。
 *  AtomicStampedReference
 *
 *
 **/
public class CASDemoABA {
    public static void main(String[] args) {
//        casABA();
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次获取版本号：" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 模拟ABA
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第二次获取版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第三次获取版本号：" + atomicStampedReference.getStamp());
        },"T3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次获取版本号：" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t第二次结果：flag = " + result + "\t" + atomicStampedReference.getStamp() + " ddf: " + atomicStampedReference.getReference());
        },"T4").start();

    }

    /**
     * 使用AtomicReference模拟CAS算法的ABA问题的产生
     */
    public static void casABA() {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        new Thread(() -> {
            atomicReference.compareAndSet(100,99);
            atomicReference.compareAndSet(99,100);
            System.out.println("######: " + atomicReference.get());
        },"T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicReference.compareAndSet(100, 888);
            System.out.println("result: " + atomicReference.get() + "\tflag: " + b);
        },"T2").start();
    }
}

