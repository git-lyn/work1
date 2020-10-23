package com.lyn.juc.leet.leetcode;

import java.lang.reflect.Proxy;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-03-18 22:42
 **/
public class FeiBoDemo {
    public static void main(String[] args) {
        System.out.println(fb1(7));
        System.out.println(fb2(7));
        MyDao o = (MyDao) Proxy.newProxyInstance(FeiBoDemo.class.getClassLoader(), new Class[]{MyDao.class}, new MyInvocationHandler(new IndexDao()));
        System.out.println("44444444");
        o.query();

    }

    public static int fb1(int num) {
        if (num == 1 || num == 2) {
            return 1;
        }

        int res = 0;
        int a = 1;
        int b = 1;
        while (num > 2) {
            res = a + b;
            a = b;
            b = res;
            num--;
        }
        return res;
    }

    public static int fb2(int num) {
        if (num == 1 || num == 2) {
            return 1;
        }else{
            return fb2(num - 1) + fb2(num - 2);
        }
    }

    public static void doTowers(int topN, char from, char inter, char to) {
        if (topN == 1){
            System.out.println("Disk 1 from "
                    + from + " to " + to);
        }else {
            doTowers(topN - 1, from, to, inter);
            System.out.println("Disk "
                    + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to);
        }
    }


    public static void towers(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.println("tower 1 from " + from + " to " + to);
        } else {
            towers(topN - 1,from,to,inter);
            System.out.println("tower " + topN + " from " + from + " to " + to);
            towers(topN - 1,inter,from,to);
        }
    }

}
