package com.lyn.juc.sf;

import org.apache.spark.sql.execution.columnar.ARRAY;

import java.util.Arrays;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-05-18 21:43
 * 插入排序算法,进行数组数据的排序
 **/
public class InsertSortDemo {
    public static void main(String[] args) {
        int[] arr = {7, 65, 3, 5, 5, 7, 24, 99, 88, 71};
        insertSort5(arr);
    }

    /**
     * 使用插入排序算法，进行数据的排序操作
     * 时间复杂度： O(n * n)
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int insertVal = arr[i];
            while (j >= 0 && arr[j] > insertVal) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = insertVal;
        }
        for (int temp : arr) {
            System.out.print(temp + " ");
        }
        System.out.println();
    }


    /**
     * 插入排序算法
     */
    public static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int insertVal = arr[i];
            while (j >= 0 && arr[j] > insertVal) {
                // 交换，非常重要的问题
                arr[j + 1] = arr[j];
                j--;
            }
            // 找到要插入的位置
            arr[j + 1] = insertVal;
        }
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 插入排序算法： 时间复杂度： 平均时间： n * n   最坏时间： n * n
     * 是否稳定： 稳定
     */
    public static void insertSort3(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int insertVal = arr[i];
            while (j >= 0 && arr[j] > insertVal) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = insertVal;
        }
        System.out.println(Arrays.toString(arr));
    }


    public static void insertSort4(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int j = i - 1;
//            while (arr[j] > insertVal && j >= 0) {
//                arr[j + 1] = arr[j];
//                j--;
//            }
            while (j >= 0 && arr[j] > insertVal) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = insertVal;
        }
        System.out.println(Arrays.toString(arr));
    }


    public static void insertSort5(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int insertVal = arr[i];
            while (j >= 0 && arr[j] > insertVal) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = insertVal;
        }
        System.out.println(Arrays.toString(arr));
    }


    public static void insertSort6(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int insertVal = arr[i];
            while (j >= 0 && arr[j] > insertVal) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = insertVal;
        }
        System.out.println(Arrays.toString(arr));
    }


}
