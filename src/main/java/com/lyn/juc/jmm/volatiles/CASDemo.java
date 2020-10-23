package com.lyn.juc.jmm.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        atomicInteger.compareAndSet(5,200);
        atomicInteger.getAndIncrement();
    }
}
