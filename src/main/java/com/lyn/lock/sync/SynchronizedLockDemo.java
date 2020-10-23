package com.lyn.lock.sync;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-08-11 11:55
 **/
public class SynchronizedLockDemo {
    public static void main(String[] args) {
        LockSync lockSync = new LockSync();
        out.println("before lock111111111111");
        out.println(ClassLayout.parseInstance(lockSync).toPrintable()); // 无锁 001

        new Thread(() ->{
        synchronized (lockSync) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            out.println(" locking  22222222");
            out.println(ClassLayout.parseInstance(lockSync).toPrintable());// 轻量锁 000
        }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->{
                    synchronized (lockSync) {
//            try {
//                //Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            out.println(" 重量级锁 locking  4444444");
            out.println(ClassLayout.parseInstance(lockSync).toPrintable());// 轻量锁 000
        }
        }).start();

//        synchronized (lockSync) {
////            try {
////                //Thread.sleep(4000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//            out.println(" 重量级锁 locking  4444444");
//            out.println(ClassLayout.parseInstance(lockSync).toPrintable());// 轻量锁 000
//        }



        out.println("after locked  3333333");
        out.println(ClassLayout.parseInstance(lockSync).toPrintable()); // 无锁 001
    }
}

class LockSync{

}
