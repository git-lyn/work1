package com.lyn.juc.sf;

import java.util.Arrays;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-05-18 21:33
 * 使用选择排序堆数组进行排序处理
 **/
public class SelectSortDemo {
    public static void main(String[] args) {
        int[] arr = {7, 65, 3, 5, 5, 7, 24, 99, 88, 71};
        selectSort4(arr);
    }

    /**
     * 使用选择排序进行数据的排序：
     * 选择排序时间复杂度： O(n * n)
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int temp : arr) {
            System.out.print(temp + " ");
        }
        System.out.println();
    }


    /**
     * 选择排序算法
     */
    public static void selectSort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 选择排序算法： 平均时间复杂度： n * n    最差时间复杂度： n * n
     *
     * @param arr
     */
    public static void selectSort3(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    public static void selectSort4(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    public static void selectSort5(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


}
