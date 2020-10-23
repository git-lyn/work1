package com.lyn.juc.atomic;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch: 闭锁： 在完成某些运算时，
 *    只有其它的所有线程的运算全部完成，当前运算才继续执行
 */

public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch downLatch = new CountDownLatch(5);
        CountLDownDemo countLDownDemo = new CountLDownDemo(downLatch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(countLDownDemo).start();
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("总共花费的时间：" + (end - start));
    }
}

class CountLDownDemo implements Runnable{
    private CountDownLatch downLatch;
    public CountLDownDemo(CountDownLatch downLatch){
        this.downLatch = downLatch;
    }

    public void run() {
        synchronized (this){
            try{
                for (int i = 0; i < 500000; i++) {
                    if(i % 2 == 0){
                        System.out.println(i);
                    }
                }
            }finally {
                downLatch.countDown();
            }
        }
    }
}
