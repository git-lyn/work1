package com.lyn.lock.sync;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-08-11 22:06
 **/
// 验证对象头的 分代年龄 标记
public class GCLock {
    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        //System.gc();
        out.println("dfdfdfsdfdsfdsf");
        //System.gc();

        out.println("before 1111111");
        out.println(ClassLayout.parseInstance(myLock).toPrintable());

        synchronized (myLock) {
            out.println(" locking 222222");
            out.println(ClassLayout.parseInstance(myLock).toPrintable());
        }



        out.println(" after 3333333");
        out.println(ClassLayout.parseInstance(myLock).toPrintable());

    }
}
