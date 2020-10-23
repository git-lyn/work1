package com.lyn.juc.gc;

import java.util.concurrent.TimeUnit;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-23 13:56
 *  配置JVM的参数类型和初始化的设置
 **/
public class GCInitialArgs {
    public static void main(String[] args) {
        System.out.println("Hello GC *******  ");
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
