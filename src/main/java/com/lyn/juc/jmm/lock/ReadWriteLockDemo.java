package com.lyn.juc.jmm.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发零，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源来，就不应该再有其它线程可以堆该资源进行读或写
 *
 *     读-读能共存
 *     读-写不能共存
 *     写-写不能共存
 *
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
           new Thread(() -> {
               cache.put(temp,String.valueOf(temp));
           },i + "").start();
        }

//        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            cache.get(1);
//        },"AA").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.get(temp);
            },i + "").start();
        }

//        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            cache.get(1);
//        },  "AA").start();
//
//        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(0);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            cache.get(2);
//        },  "BB").start();
    }
}

class Cache{
    private volatile Map<Integer,Object> map = new HashMap<>();
//    {
//        map.put(1,1 + "");
//        map.put(2,2 + "");
//        map.put(3,3 + "");
//    }
    Lock lock = new ReentrantLock();
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    // 写操作
    public  void put(int key,Object value){
        rwLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t开始写入");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        }finally {
            rwLock.writeLock().unlock();
        }
    }
    // 读操作
    public void get(int key){
        rwLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t开始读取");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t读取完成:  " + map.get(key));
        }finally {
            rwLock.readLock().unlock();
        }
    }
}
