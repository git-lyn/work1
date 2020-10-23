package com.lyn.juc.jmm.volatiles;

import sun.util.locale.provider.TimeZoneNameUtility;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 使用版本号或者时间戳处理CAS产生的ABA的问题
 */
public class AtomicStampReferenDemo {


    public static void main(String[] args) {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

//        new Thread(() -> {
//            atomicReference.compareAndSet(100,101);
//        },"AAA").start();

        new Thread(() -> {
            // 获取当前的版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号：" + stamp);
            // 暂停1秒钟t3线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第二次版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第三次版本号：" + atomicStampedReference.getStamp());
        },"t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号：" + stamp);
            // 暂停3秒钟t4线程，保证上面的t3线程完成了一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 1024, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t修改结果：" + result + "\t当前实际的版本号为："+ atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "\t当前实际最新值：" + atomicStampedReference.getReference());
        },"t4").start();

    }
}

