package com.lyn.lock.sync;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-08-11 15:07
 **/
public class PianXiangLock {
    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);  // sleep在对应的后面就不是偏向锁了
        MyLock myLock = new MyLock();
        out.println("before 11111");
        out.println(ClassLayout.parseInstance(myLock).toPrintable());


        Thread t1 = new Thread(() ->{
            synchronized (myLock) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.println("lock。。。。。。");
                out.println(ClassLayout.parseInstance(myLock).toPrintable());
            }
        });
        t1.start();
//        t1.join();

        Thread t2 = new Thread(() ->{
            synchronized (myLock) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.println(" t2  lock。。。。。。");
                out.println(ClassLayout.parseInstance(myLock).toPrintable());
            }
        });
        t2.start();
//        t2.join();




//        new Thread(() ->{
//            synchronized (myLock) {
////                try {
////                    Thread.sleep(1000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                out.println("重量级锁");
//                out.println(ClassLayout.parseInstance(myLock).toPrintable());
//            }
//            out.println("重量级锁22333333333");
//            out.println(ClassLayout.parseInstance(myLock).toPrintable());
//        }).start();

        Thread.sleep(1000);

        synchronized (myLock) {
            out.println("locking 222222");
            out.println(ClassLayout.parseInstance(myLock).toPrintable());
        }

        out.println("after 3333333");
        out.println(ClassLayout.parseInstance(myLock).toPrintable());

    }
}
