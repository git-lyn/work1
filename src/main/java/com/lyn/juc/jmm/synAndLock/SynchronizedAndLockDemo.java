package com.lyn.juc.jmm.synAndLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class SynchronizedAndLockDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().totalMemory());
        synchronized (new Object()){
        }
        new ReentrantLock();
    }
}

