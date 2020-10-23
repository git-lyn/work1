package com.lyn.juc.jmm.arraylist;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-12 14:05
 **/
public class ArrayListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for(int i = 0;i < 20;i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },"ddd: " + i).start();
        }

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (String s : list) {
            System.out.println(s);
        }
    }
}
