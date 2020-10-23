package com.lyn.juc.threadpool;

import org.apache.avro.TestAnnotation;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-14 16:41
 **/
public class PrintNumberAndCaseDemo {
    public static void main(String[] args) {
        test1();
//        sync();
    }

    public static void sync(){
        PrintNumberAndCase numberAndCase = new PrintNumberAndCase();
        new Thread(() ->{
            for(int i = 1;i <= 52;i++) {
                numberAndCase.synNum(i);
            }
        },"11111").start();

        new Thread(() ->{
            for(int i = 65;i <= 90;i++) {
                numberAndCase.syncCase(i);
            }
        },"22222").start();
    }

    public static void  test1(){
        PrintNumberAndCase numberAndCase = new PrintNumberAndCase();
        new Thread(() ->{
            for(int i = 1;i <= 52;i++) {
                numberAndCase.num(i);
            }
        },"11111").start();

        new Thread(() ->{
            for(int i = 65;i <= 90;i++) {
                numberAndCase.upcase(i);
            }
        },"22222").start();
    }

    @Test
    public void test() {
        char a = 'a';
        char A = 'A';
        System.out.println((a + 0) + ": " + (A + 0));
    }
}

/**
 * 有两个线程：
 * 一个打印 1-52， 一个线程打印A-Z
 * 1 2 A 3 4 B 5 6 C
 */
class PrintNumberAndCase {
    private Semaphore semaphore1 = new Semaphore(1);
    private Semaphore semaphore2 = new Semaphore(0);
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private int flag = 0;


    public void num(int temp) {
        try {
            lock.lock();
            while (flag == 2) {
                flag = 0;
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + " 数字: " + temp);
            flag++;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 打印字母
    public void upcase(int temp) {
        //
        try {
            lock.lock();
            condition1.signal();
            System.out.println(Thread.currentThread().getName() + " 字母: " + (char)temp);
            condition2.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void number(int temp) {
        try {
            semaphore1.acquire();
            while (flag != 2) {
                System.out.println(Thread.currentThread().getName() + "");
            }
            flag = 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore2.release();
        }
    }
    // 打印数字
    public synchronized void synNum(int temp){
        while (flag == 2) {
            flag = 0;
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + temp);
        flag++;
        this.notify();
    }
    private int res = 0;
    public synchronized void syncCase(int temp){
        while (res == 1) {
            try {
                res = 0;
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + (char)temp);
        res++;
        this.notify();

    }


}
