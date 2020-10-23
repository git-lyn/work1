package com.lyn.juc.jmm.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-20 17:13
 *   使用CAS实现一个线程安全的计数器
 *
 **/
public class CASCountDemo {
    public static void main(String[] args) {
        CASCount casCount = new CASCount();
        for (int i = 1; i <= 100; i++) {
            int temp = i;
            new Thread(() -> {
//                casCount.increment();
                casCount.addAsr();
                System.out.println(Thread.currentThread().getName() + "\t" + temp);
            },i + "线程： ").start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + casCount.getAsr());
    }
}
// cas算法实现的一个计数器
class CASCount{
    AtomicReference<Integer> atomicReference = new AtomicReference<>(0);
    int a = 0;
    public int get(){
        return atomicReference.get();
    }
    // 实现自增的操作
    public void increment(){
        int num;
        do{
            num = atomicReference.get();
        }while (!atomicReference.compareAndSet(num,num+1));
//        return num + 1;
    }

    public void add(){
        a++;
    }


    private AtomicStampedReference<Integer>  asr = new AtomicStampedReference<>(0,1);

    public int getAsr(){
        return asr.getReference();
    }

    public void addAsr(){
        int var5;
        do{
            var5 = asr.getReference();
        }while(!asr.compareAndSet(var5,var5 + 1,asr.getStamp(),asr.getStamp() + 1));
    }

}

/**
 * 模拟实现CAS的内部原理
 */
class MyCAS{
    private int number = 0;
    private boolean flag = false;

    public int get(){
        return number;
    }

    public boolean compareAndSweep(int expect,int result){

        return flag;
    }



}
