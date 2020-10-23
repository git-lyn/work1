package com.lyn.juc.jmm.containers;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 写时复制
 *  往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行copy，
 *  复制出一个新的容器Object[] newElements,然后新的容器Object[] newElements里添加元素，添加元素之后，
 *  再将原容器的引用指向新的容器setArray(newElements);这样做的好处时可以堆CopyOnWrite容器进行并发的读，
 *  而不需要加锁，因为当前容器不会添加任何元素，所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的
 *  容器
 *
 *
 *      public boolean add(E e) {
         final ReentrantLock lock = this.lock;
         lock.lock();
         try {
         Object[] elements = getArray();
         int len = elements.length;
         Object[] newElements = Arrays.copyOf(elements, len + 1);
         newElements[len] = e;
         setArray(newElements);
         return true;
         } finally {
         lock.unlock();
         }
         }
 *
 */
public class ListNotSafeDemo {
    public static void main(String[] args) {
//        copyList();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 28; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString());
                System.out.println(set);
            },String.valueOf(i)).start();
        }

    }

    public static void copyList() {
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
                for (String s : list) {
                    System.out.println(s);
                }
            },String.valueOf(i)).start();
        }
    }
}
