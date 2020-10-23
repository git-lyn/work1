package com.lyn.juc.jmm.jvmXX;

import java.util.UUID;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-06 17:00
 **/
public class RandomJstatGC {
    public static void main(String[] args) {
        while (true){
            System.out.println(new String(UUID.randomUUID().toString().substring(0,4)));
        }
    }
}
