package com.lyn.juc.jmm.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-24 19:47
 **/
public class MyLock {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        Thread t1 = Thread.currentThread();
        Thread t2 = Thread.currentThread();
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t1 == t2);

        /*
           public void lock(){
           if(compareAndSetState(0,1){
              setExclusiveOwnerThread(Thread.currentThread());
           }
           else
           acquire(1);
           if(!tryAcquire(1) && acquireQueued(addWaiter(Node.EXCLUSIVE),args);

           nonfairTryAcquire(1);

        }

         */


    }
}
