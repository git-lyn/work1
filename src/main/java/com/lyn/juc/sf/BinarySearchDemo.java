package com.lyn.juc.sf;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-05-19 15:46
 *   使用二分查找算法进行有序数据的查找
 *     对应的时间复杂度为： O(log n)
 *
 **/
public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 24, 65, 71, 88, 99};
        System.out.println(binarySearch2(arr,99));
    }

    public static int binarySearch(int[] arr,int num) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (arr[middle] == num) {
                return middle;
            }else{
                if (arr[middle] > num) {
                    high = middle - 1;
                }else{
                    low = middle + 1;
                }
            }
        }
        return  -999;
    }

    /**
     * 采用二分查找算法进行数据的查找
     */
    public static int binarySearch2(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (arr[middle] == num) {
                return middle;
            }else{
                if (arr[middle] > num) {
                    high = middle - 1;
                }else{
                    low = middle + 1;
                }
            }
        }
        return -999;
    }


    public static int binarySearch3(int[] arr,int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int middle = (low + high) / 2;
            if (arr[middle] == target) {
                return middle;
            } else if (arr[middle] > target) {
                high = middle - 1;
            } else if (arr[middle] < target) {
                low = middle + 1;
            }

        }
        return  -99;
    }


}
