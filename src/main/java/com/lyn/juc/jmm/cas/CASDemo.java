package com.lyn.juc.jmm.cas;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-19 16:49
 **/
public class CASDemo {
    private long valueOffset = 1;
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println(atomicInteger.getAndIncrement());
    }

//    public final int getAndIncrement(){
//        return getAndAddInt(this,valueOffset,1);
//    }
//
//    private int getAndAddInt(CASDemo var1, long var2, int ivar4) {
//        int var5;
//        do{
//            var5 = getIntVolatile(var1,var2);
//        }while (!compareAndSwapInt(var1,var2,var5,var5 + ivar4));
//        return var5;
//    }

    private static Unsafe unsafe22 = Unsafe.getUnsafe();;

    /**
     * 手写一个自旋锁
     */
    public static int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do{
            var5 = unsafe.getIntVolatile(var1,var2);
        }while(!unsafe.compareAndSwapInt(var1,var2,var5,var5 + var4));
        return var5;
    }



}
