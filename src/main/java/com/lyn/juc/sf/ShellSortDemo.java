package com.lyn.juc.sf;

import java.util.Arrays;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-05-18 21:54
 *   使用shell排序堆数组数据进行排序操作
 *   时间复杂度： O( n * log n)
 **/
public class ShellSortDemo {
    public static void main(String[] args) {
        int[] arr = {7, 65, 3, 5,5,7, 24, 99, 88, 71};
//        shellSort3(arr);
        shellSort4(arr);
    }

    /**
     * 使用shell排序算法进行数据的排序操作
     *  对应的时间复杂度： O(n * log n)
     * @param arr
     */
    public static void shellSort(int[] arr) {
        // 设置排序间隔
        int h = 1;
        while (h <= arr.length) {
            h = h * 3 + 1;
        }

        while (h > 0) {
            for(int i = h;i < arr.length;i+=h) {
                int j = i - h;
                int insertVal = arr[i];
                while (j >= 0 && arr[j] > insertVal) {
                    arr[j + h] = arr[j];
                    j -= h;
                }
                arr[j + h] = insertVal;
            }
            h = (h - 1) / 3;
        }
        for (int temp : arr) {
            System.out.print(temp + " ");
        }
        System.out.println();
    }


    /**
     * 采用shell排序算法进行排序
     */
    public static void shellSort2(int[] arr) {
        // 设置插入的间隔
        int h = 1;
        while (h <= arr.length) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for(int i = h;i < arr.length;i+= h) {
                int j = i - h;
                int insertVal = arr[i];
                while (j >= 0 && arr[j] > insertVal) {
                    arr[j + h] = arr[j];
                    j-= h;
                }
                arr[j + h] = insertVal;
            }
            h = (h - 1) / 3;
        }
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 采用shell算法进行数据的排序
     */
    public static void shellSort3(int[] arr) {
        // 设置排序的间隔
        int h = 1;
        while (h < arr.length) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for(int i = h;i < arr.length;i+= h) {
                int j = i - h;
                int insertVal = arr[i];
                while (j >= 0 && arr[j] > insertVal) {
                    arr[j + h] = arr[j];
                    j -= h;
                }
                arr[j + h] = insertVal;
            }
            h = (h - 1) / 3;
        }
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 使用shell算法，进行数据的排序操作
     * 对应的时间复杂度： 平均时间 n * log n  最差时间： n * n
     * 是否稳定： 不稳定
     */

    public static void shellSort12(int[] arr) {
        int gap = 1;
        // 查找最大数组间隔
        while (gap < arr.length) {
            gap = gap * 3 + 1;
    }

//        while (h > 0) {
//            for(int i = h;i < arr.length;i+= h) {
//                int j = i - h;
//                int insertVal = arr[i];
//                while (j >= 0 && arr[j] > insertVal) {
//                    arr[j + h] = arr[j];
//                    j -= h;
//                }
//                arr[j + h] = insertVal;
//            }
//            h = (h - 1) / 3;
//        }

        while (gap > 0) {
            for(int i = gap;i < arr.length;i += gap) {
                int j = i - gap;
                // 要插入的数据
                int insertVal = arr[i];
                while (j >= 0 && arr[j] > insertVal) {
//                    arr[j] = arr[j - gap];
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = insertVal;
            }
            gap = (gap - 1) / 3;
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * shell排序算法： 时间复杂度： 平均时间复杂度 n * log n   最差时间复杂度 n * n
     *    是否稳定： 不稳定
     * @param arr
     */
    public static void shellSort13(int[] arr) {
        int gap = 1;
        while (gap < arr.length) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for(int i = gap;i < arr.length;i += gap) {
                int j = i - gap;
                int insertVal = arr[i];
                while (j >= 0 && arr[j] > insertVal) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = insertVal;
            }
            gap = (gap - 1) / 3;
        }
        System.out.println(Arrays.toString(arr));
    }

    // shell排序算法
    public static void shellSort4(int[] arr) {
        // 设置排序间隔
        int gap = 1;
        int sum = arr.length;
        while (gap <= sum) {
            gap = gap * 3 + 1;
        }
        // 使用插入排序算法进行排序处理
        while (gap > 0) {
            for(int i = gap;i < arr.length;i+=gap) {
                int j = i - gap;
                int insertVal = arr[i];
                while (j >= 0 && arr[j] > insertVal) {
                    arr[j + gap] = arr[j];
                    j-=gap;
                }
                arr[j + gap] = insertVal;
            }
            gap = (gap - 1) / 3;
        }
        System.out.println(Arrays.toString(arr));
    }




}
