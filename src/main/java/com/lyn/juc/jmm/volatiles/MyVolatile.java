package com.lyn.juc.jmm.volatiles;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-04-19 15:32
 **/
public class MyVolatile {
    volatile int number = 0;
    public void add(){
        this.number = 60;
    }
    public void plus(){
        number++;
    }

    public static void main(String[] args) {

    }
}
