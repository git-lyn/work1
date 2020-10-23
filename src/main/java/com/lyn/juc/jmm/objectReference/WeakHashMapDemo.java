package com.lyn.juc.jmm.objectReference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-17 14:00
 *   WeakHashMap: 弱HashMap    只要发生GC，map中的数据就会被移除。
 **/
public class WeakHashMapDemo {
    public static void main(String[] args) {
        hashMap();
        weakHashMap();
    }

    private static void hashMap() {
        Map<Integer,String> map = new HashMap<>();
        Integer integer = new Integer(2);
        map.put(integer,"weakHashMap");
        System.out.println(map);
        integer = null;
        System.gc();
        System.out.println(map);
    }

    private static void weakHashMap() {
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer integer = new Integer(1);
        map.put(integer,"map");
        System.out.println(map);
        integer = null;
        System.gc();
        System.out.println(map);
        System.out.println("######");
    }
}
