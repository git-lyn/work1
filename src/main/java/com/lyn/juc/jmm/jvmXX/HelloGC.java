package com.lyn.juc.jmm.jvmXX;

import java.util.concurrent.TimeUnit;

import static java.lang.Integer.MAX_VALUE;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-16 15:39
 *
 * 配置JVM的参数
 **/
public class HelloGC {
    public static void main(String[] args) throws Exception {
        // 默认修改参数
        // -Xms128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
        System.out.println("Hello GC");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
