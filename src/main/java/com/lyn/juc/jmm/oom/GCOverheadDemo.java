package com.lyn.juc.jmm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-17 21:11
 *
 *  JVM参数配置演示：
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 *  GC回收时间过长会抛出OutOfmemoryError。过长的定义是，超过98%的时间用来做GC并且回收了不到2%的堆内存
 *  连续多次GC都只是回收了不到2%的极端情况下才会抛出。加入不抛出GC overhead limit 错误会发生什么情况呢？
 *  那就是GC清理的额这么点内存很快会再次填满，迫使GC再次执行，这样就形成恶行循环，
 *  CPU使用率一直都是100%，而GC却没有任何成果。
 *
 **/
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try{
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e){
            System.out.println("******i: " + i);
            e.printStackTrace();
        }
    }
}
