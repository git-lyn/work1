package com.lyn.juc.jmm.collections;

import java.util.ArrayList;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-19 19:41
 **/
public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
    }
}
