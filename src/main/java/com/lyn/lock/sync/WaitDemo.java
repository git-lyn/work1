package com.lyn.lock.sync;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-08-11 12:48
 **/
// 调用wait立刻变成重量级锁
public class WaitDemo {
    public static void main(String[] args) throws Exception{
        MyLock myLock = new MyLock();

        new Thread(() ->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (myLock) {
                out.println("before lock111111111111");
                out.println(ClassLayout.parseInstance(myLock).toPrintable());
                try {
                    myLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                out.println("after waiting lock22222222222");
                out.println(ClassLayout.parseInstance(myLock).toPrintable());
            }
        }).start();




        new Thread(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myLock.notifyAll();
        }).start();

        out.println("after waiting 3333333");
        out.println(ClassLayout.parseInstance(myLock).toPrintable());
    }
}
