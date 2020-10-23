package com.lyn.juc.ds.dynamic;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-05-16 17:24
 *  动态规划的问题处理
 **/
public class DongTaiGuiHuaDemo {
    public static void main(String[] args) {
        System.out.println(pbnqsl(6));
    }

    /**
     * 处理斐波那契数列
     */
    public static int pbnqsl(int number){
        if(number == 1 || number == 2){
            return 1;
        }
        int a = 1;
        int b = 1;
        int result = 0;
        while (number >= 3){
            result = a + b;
            a = b;
            b = result;
            number--;
        }
        return result;
    }
}
