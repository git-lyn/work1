package com.lyn.juc.atomic;


public class TestProAndCon {
    public static void main(String[] args) {
        MyClerk myClerk = new MyClerk();
        new Thread(new MyProductors(myClerk),"生产者A").start();
        new Thread(new MyProductors(myClerk),"生产者C").start();
        new Thread(new MyConsumer(myClerk),"消费者B").start();
    }
}

class MyClerk{
    private int product = 0;
    // 生产产品
    public synchronized void create(){
        while(product < 1){
            System.out.println(Thread.currentThread().getName() + " 生产第" + ++product + " 号产品");
            this.notifyAll();
        }
            System.out.println("产品已满！！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    public synchronized void create11(){
        if(product > 1){
            System.out.println("产品已满！！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(Thread.currentThread().getName() + " 生产第" + ++product + " 号产品");
            this.notifyAll();
        }
    }
    // 消费产品
    public synchronized void consumer(){
        while(product <= 0){
            System.out.println("没有数据，请尽快生产!!!!");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(Thread.currentThread().getName() + " 消费第" + --product + "号商品");
            this.notifyAll();

    }
}

class MyProductors implements Runnable{
    private MyClerk myClerk;
    public MyProductors(MyClerk myClerk){
        this.myClerk = myClerk;
    }
    public void run() {
        for (int i = 0; i < 20; i++) {
            myClerk.create();
        }
    }
}

class MyConsumer implements Runnable{
    private MyClerk myClerk;
    public MyConsumer(MyClerk myClerk){
        this.myClerk = myClerk;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            myClerk.consumer();
        }
    }
}