package com.lyn.juc.jmm.oom;

import java.util.Random;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-17 16:19
 *  原空间内存溢出： java.lang.OutofMemoryError:Metaspace
 *
 *  JVM参数
 *  -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
 *  java8 及之后的版本使用Metaspace来替代永久代
 *
 *  Metaspace是方法区在HotSpot中的实现，它与持久代最大的区别在于：Metaspace并不在续集及内存中而是使用本地内存
 *  也即在java8中，class 被存储在叫做Metaspace的native memory
 *
 *  永久代（java8后被原空间Metaspace取代了）存放了以下信息：
 *
 *  虚拟机加载的类信息
 *  常量池
 *  静态变量
 *  即时编译后的代码
 *
 * 模拟Metaspace空间溢出，我们不断生成类往元空间灌，类占据的空间总是会超过Metaspace指定的空间大小
 *
 **/
public class MetaspaceOOMDemo {
//    private static byte[] bytes = new byte[300 * 1024 * 1024];
//    private static String str = "hello";


    static class  MetaspaceOOM{

    }

    public static void main(String[] args) {
        int i = 0; // 模拟计数多少次译后发生异常
        try{
            while (true){
//            str += new Random().nextInt(10000);
                i++;
                MetaspaceOOM metaspaceOOMi = new MetaspaceOOM();
            }
        }catch (Throwable throwable){
            System.out.println("**********  " + i);
            throwable.getMessage();
        }
    }
}
