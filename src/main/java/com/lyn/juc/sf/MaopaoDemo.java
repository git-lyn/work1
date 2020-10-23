package com.lyn.juc.sf;

import java.util.Arrays;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-05-18 21:15
 *   冒泡排序算法
 **/
public class MaopaoDemo {
    public static void main(String[] args) {
        int[] arr = {7, 65, 3, 5, 24, 99, 88, 71};
        mp4(arr);
    }

    /**
     * 使用冒泡排序对数据进行排序：
     *  时间复杂度： O(n*n)
     * @param arr
     */
    public static void mp(int[] arr) {
        for(int i = 0;i < arr.length - 1;i++) {
            for(int j = 0;j < arr.length - i - 1;j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for (int temp : arr) {
            System.out.print(temp + " ");
        }
        System.out.println();
    }


    /**
     * 冒泡排序算法
     */
    public static void mp2(int[] arr) {
        for(int i = 0;i < arr.length - 1;i++) {
            for(int j = 0;j < arr.length - 1 - i;j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                 }
            }
        }

        System.out.println(Arrays.toString(arr));
    }


    /**
     * 冒泡排序算法： 最差时间复杂度： n * n   平均时间复杂度： n * n
     * 是否稳定： 稳定
     * 稳定排序算法;   冒泡排序算法、插入排序算法、归并排序算法
     */
    public static void mp3(int[] arr) {
        for(int i = 0;i < arr.length - 1;i++) {
            for(int j = 0;j < arr.length - 1 - i;j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    public static void mp4(int[] arr) {
        for(int i = 0;i < arr.length - 1;i++) {
            for(int j = 0;j < arr.length - i - 1;j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }





    public static void mp5(int[] arr) {
        for(int i = 0;i < arr.length - 1;i++) {
            for(int j = 0;j < arr.length - i - 1;j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }









}
