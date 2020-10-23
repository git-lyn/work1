package com.lyn.juc.jmm.threadCondition;

import com.lyn.juc.threadpool.TestHasBlockingQueueThreadPool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-05-21 13:22
 *   线程操纵资源类，进行多线程数据的处理
 *
 **/
public class ConditionPrintDemo {
    public static void main(String[] args) {
        char c = 'A';
        System.out.println(c + 0);
        char b = 'a';
        System.out.println(b + 0);
        System.out.println((char)92);
/*        Worker worker = new Worker();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                worker.product();
            }
        }).start();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                worker.consumer();
            }
        }).start(); */


        // 打印 1 -> A -> 2 -> B -> 3 -> C

        PrintNumAndStr numAndStr = new PrintNumAndStr();
        new Thread(() ->{
            for (int i = 1; i <= 26; i++) {
//                numAndStr.printNum(i);
                // 采用lock的方式进行处理
//                numAndStr.lockNum(i);
                numAndStr.semaphoreNum(i);
            }
        }).start();

        new Thread(() ->{
            for (int i = 65; i <= 91; i++) {
//                numAndStr.printStr(i);
//                numAndStr.lockStr(i);
                numAndStr.semaphoreStr(i);
            }
        }).start();


        /**
         * 三个线程交替打印：A、B、C打印10遍
         *
         */
      /*  LockThreeStr lockThreeStr = new LockThreeStr();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
//                lockThreeStr.printA();
//                lockThreeStr.syncA();
                lockThreeStr.semaphoreA();
            }
        }).start();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
//                lockThreeStr.printB();
//                lockThreeStr.syncB();
                lockThreeStr.semaphoreB();
            }
        }).start();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
//                lockThreeStr.printC();
//                lockThreeStr.syncC();
                lockThreeStr.semaphoreC();
            }
        }).start();*/
    }


}

// 线程操纵资源类
class Worker{
    private int productor = 0;
    // 生产产品
    public synchronized void product(){
            // 判断
            while (productor >= 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 工作
//            productor++;
            System.out.println(Thread.currentThread().getName() + "\t 生产的产品编号为： " + productor++);
//            lock.newCondition().signal();
            notifyAll();
    }
    // 消费产品
    public synchronized void consumer(){
        try{
            // 判断
            while (productor <= 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 工作
//            productor--;
            System.out.println(Thread.currentThread().getName() + "\t 消费的产品编号为：" + productor--);
//            lock.newCondition().signal();
            // 唤醒
        }finally {
            notifyAll();
        }
    }
}

/**
 * 两个线程进行交替打印：
 *    1 -> A -> 2 -> B -> 3 -> C
 */
class PrintNumAndStr{
    private int num = 1;
    private int str = 63;
    Lock lockNum = new ReentrantLock();
//    Lock lockStr = new ReentrantLock();
    Condition condition1 = lockNum.newCondition();
    Condition condition2 = lockNum.newCondition();

    /**
     * 打印数字
     * @param number
     */
    public synchronized void printNum(int number){
        // 唤醒
        this.notifyAll();
        // 判断
//        while (number % 2 == 0) {
        //工作
        System.out.println(Thread.currentThread().getName() + ": " + number);
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void printStr(int str) {
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + ": " + (char)str);
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 采用lock的方式进行处理
     * @param num
     */
    public void lockNum(int num){
        lockNum.lock();
        condition2.signal();
        try{
            System.out.println(Thread.currentThread().getName() + ": " + num);
            try {
                condition1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            lockNum.unlock();
        }

    }

    public void lockStr(int num){
        lockNum.lock();
        condition1.signal();
        try{
            System.out.println(Thread.currentThread().getName() + ": " + (char)num);
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            lockNum.unlock();
        }
    }

    /**
     * 通过信号量的方式打印数字和字符串
     */
    Semaphore semaphore1 = new Semaphore(1);
    Semaphore semaphore2 = new Semaphore(0);

    public void semaphoreNum(int num){
        try {
            semaphore1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": " + num);
        semaphore2.release();
    }

    public void semaphoreStr(int num){
        try {
            semaphore2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": " + (char)num);
        semaphore1.release();
    }
}

/**
 * 三个线程进行交替打印：A、B、C
 */
// 使用Lock的方式
class LockThreeStr{
    private int flag = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    Semaphore semaphore1 = new Semaphore(1);
    Semaphore semaphore2 = new Semaphore(0);
    Semaphore semaphore3 = new Semaphore(0);

    public void printA(){
        lock.lock();
        try{
            // 判断
            while (flag != 1) {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 工作
            System.out.println(Thread.currentThread().getName() + ": " + "A");
            flag = 2;
            // 通知
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try{
            // 判断
            while (flag != 2) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 工作
            System.out.println(Thread.currentThread().getName() + ": " + "B");
            flag = 3;
            // 通知
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try{
            // 判断
            while (flag != 3) {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 工作
            System.out.println(Thread.currentThread().getName() + ": " + "C");
            flag = 1;
            // 通知
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 通过sychronized
     */
    public synchronized void syncA(){

        // 判断
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 工作
        System.out.println(Thread.currentThread().getName() + ": " + "A");
        flag = 2;
        // 唤醒
        this.notifyAll();
    }

    public synchronized void syncB(){
        // 判断
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 工作
        System.out.println(Thread.currentThread().getName() + ": " + "B");
        flag = 3;
        // 唤醒
        this.notifyAll();

    }

    public synchronized void syncC(){

        // 判断
        while (flag != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 工作
        System.out.println(Thread.currentThread().getName() + ": " + "C");
        flag = 1;
        // 唤醒
        this.notifyAll();
    }

    /**
     * 通过信号量的方式Semaphore
     */
    public void semaphoreA() {
        // 判断
        try {
            semaphore1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 工作
        System.out.println(Thread.currentThread().getName() + ": " + "A");
        semaphore2.release();
        // 通知
    }

    public void semaphoreB() {
        // 判断
        try {
            semaphore2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 工作
        System.out.println(Thread.currentThread().getName() + ": " + "B");
        semaphore3.release();
        // 通知
    }

    public void semaphoreC() {
        // 判断
        try {
            semaphore3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 工作
        System.out.println(Thread.currentThread().getName() + ": " + "C");
        semaphore1.release();
        // 通知
    }


}

