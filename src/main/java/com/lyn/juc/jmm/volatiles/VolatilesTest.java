package com.lyn.juc.jmm.volatiles;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-19 14:24
 **/
public class VolatilesTest {
     static volatile int a = 3;
     public static void add(){
         a = 5;
     }
    public static void main(String[] args) {
         add();
    }
}

