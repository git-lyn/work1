package com.lyn.juc.jmm.threadCondition.productAndConsumer;

/**
 *  生产者消费者模式处理
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-13 16:36
 **/
public class PCDemo {
    public static void main(String[] args) {
        myPro();
    }

    public static void myPro(){
        MyPro myPro = new MyPro();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                myPro.product();
            }
        },"生产者AAAAA").start();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                myPro.consumer();
            }
        },"消费者11111").start();
    }

}

class MyPro{
    private int duct = 0;
    public synchronized void product(){
        while (duct != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 生成产品：" + (++duct));
        this.notifyAll();
    }

    public synchronized void consumer(){
        while (duct == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 消费产品:   " + (--duct));
        this.notifyAll();
    }
}
