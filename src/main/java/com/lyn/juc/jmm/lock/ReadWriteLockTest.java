package com.lyn.juc.jmm.lock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-12 19:05
 **/
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache test = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int a = i;
            new Thread(() -> {
                test.write(a);
            }, "线程" + i + ": ").start();
        }


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            final int a = i;
            new Thread(() -> {
                test.read(a);
            }, "线程" + i + ": ").start();
        }

    }
}

class MyCache {
    private volatile Map<Integer, String> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // 写入数据
    public void write(int key) {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " 开始写入... ");
            map.put(key, key + "");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 写入完成... ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 读取数据
    public void read(int key) {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " 读取数据### ");
            System.out.println(Thread.currentThread().getName() + " ###完成读取  " + map.get(key));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }

    }

}
