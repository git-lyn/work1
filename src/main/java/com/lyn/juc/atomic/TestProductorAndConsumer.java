package com.lyn.juc.atomic;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

import java.awt.font.TextHitInfo;

public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(productor,"生产者A").start();
        new Thread(consumer,"消费者B").start();
        new Thread(productor,"生产者C").start();
        new Thread(consumer,"消费者D").start();
    }
}

// 创建对应的店员
class Clerk{

    private int product = 0;
    // 生产商品
    public synchronized void get(){
        while(product < 1){
            System.out.println(Thread.currentThread().getName() + "生产了第 " + ++product + " 号商品");;
            this.notifyAll();
        }
            System.out.println("商品已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
    // 消费商品
    public synchronized void sale11(){
        while (product > 0){
            System.out.println(Thread.currentThread().getName() + "消费了第 " + --product + "号商品");;
            this.notifyAll();
        }
            System.out.println("已经没有商品了");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }

    // 消费商品
    public synchronized void sale(){
        while(product <= 0){
            System.out.println("已经没有商品了");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "消费了第 " + --product + "号商品");;
        this.notifyAll();
    }

}
// 生产者
class Productor implements Runnable{
    private Clerk clerk;
    public Productor(Clerk clerk){
        this.clerk = clerk;
    }
    public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clerk.get();
        }
    }
}
// 消费者
class Consumer implements Runnable{
    private Clerk clerk;
    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }
    public void run() {
        for (int i = 0; i < 20; i++) {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            clerk.sale();
        }
    }
}