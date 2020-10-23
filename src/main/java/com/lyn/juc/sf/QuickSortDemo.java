package com.lyn.juc.sf;

import sun.util.locale.provider.TimeZoneNameUtility;

import java.util.concurrent.TimeUnit;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-05-18 22:49
 *  使用快速排序算法进行数据的排序
 *   对应的时间复杂度： O(n * log n)
 **/
public class QuickSortDemo {
    public static void main(String[] args) {
        int[] arr = {7, 65, 3, 5,5,7, 24, 99, 88, 71};
//        quickSort3(0,arr.length - 1,arr);
//        quickSort3(0,arr.length - 1,arr);
//        quicksort44(0,arr.length - 1,arr);
        quickSort105(arr,0,arr.length - 1);
//        try {
//            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        for (int temp : arr) {
            System.out.print(temp + " ");
        }
        System.out.println();
    }

    public static void quickSort3(int left,int right,int[] arr){
        int l = left;
        int r = right;
        int mind = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < mind) {
                l++;
            }
            while (arr[r] > mind) {
                r--;
            }
            if (l >= r) {
                break;
            }
            // 交换两个数据
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == mind) {
                r--;
            }
            if (arr[r] == mind) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        if (l < right) {
            quickSort3(l,right,arr);
        }
        if (left < r) {
            quickSort3(left,r,arr);
        }
    }



    public static void quickSort2(int left, int right, int[] arr) {
        int l = left;
        int r = right;
        int mind = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < mind) {
                l++;
            }
            while (arr[r] > mind) {
                r--;
            }
            if (l >= r) {
                break;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 处理后，如果发现数据相同
            if (arr[l] == mind) {
                r -= 1;
            }
            if (arr[r] == mind) {
                l += 1;
            }
        }

        if (l == r) {
            l += 1;
            r -= 1;
        }
        if (left < r) {
            quickSort2(left, r, arr);
        }
        if (l < right) {
            quickSort2(l, right, arr);
        }
    }

    public static void quickSort4(int left,int right,int[] arr) {
        int l = left;
        int r = right;
        int mind = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < mind) {
                l++;
            }
            while (arr[r] > mind) {
                r--;
            }
            if (l >= r) {
                break;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == mind) {
                r--;
            }
            if (arr[r] == mind) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        if (l < right) {
            quickSort4(l, right, arr);
        }
        if (left < r) {
            quickSort4(left,r,arr);
        }
    }




    /**
     *  使用快速排序算法进行数据的排序
     */
    public static void quickSort5(int left,int right,int[] arr) {
        int l = left;
        int r = right;
        int val = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < val) {
                l++;
            }
            while (arr[r] > val) {
                r--;
            }
            if (l >= r) {
                break;
            }
            // 进行数据的交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 进行数据的判断
            if (arr[l] == val) {
                r--;
            }
            // 右边数据的判断操作
            if (arr[r] == val) {
                l++;
            }
        }
        // 进行数据的处理
        if (l == r) {
            l++;
            r--;
        }
        //  进行递归数据判断处理
        // 进行左边数据的递归的处理
        if (l < right) {
            quickSort5(l,right,arr);
        }
        // 进行右边数据的递归的操作
        if (left < r) {
            quickSort5(left,r,arr);
        }
    }


    /**
     * 使用快速排序算法进行数据的排序
     */
    public static void quickSort6(int left, int right, int[] arr) {
        int l = left;
        int r = right;
        int val = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < val) {
                l++;
            }
            while (arr[r] > val) {
                r--;
            }
            if (l >= r) {
                break;
            }
            // 交换两个数据
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == val) {
                r--;
            }
            if (arr[r] == val) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        // 进行递归处理
        // 左边进行递归
        if (l < right) {
            quickSort6(l,right,arr);
        }
        // 右边进行递归处理
        if (left < r) {
            quickSort6(left,r,arr);
        }
    }






    /**
     *    快速排序算法： 最好情况：n * log n  最坏情况： n * n
     *
     */

    public static void quickSort7(int left,int right,int[] arr){
//        int l = left;
//        int r = right;
//        int mind = arr[(left + right)] / 2;

//        while (l < r) {
//            while (arr[l] < mind) {
//                l++;
//            }
//            while (arr[r] > mind) {
//                r--;
//            }
//            if (l >= r) {
//                break;
//            }
//            // 交换两个数据
//            int temp = arr[l];
//            arr[l] = arr[r];
//            arr[r] = temp;
//            if (arr[l] == mind) {
//                r--;
//            }
//            if (arr[r] == mind) {
//                l++;
//            }

//        }

        int l = left;
        int r = right;
        int mind = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < mind) {
                l++;
            }
            while (arr[r] > mind) {
                r--;
            }
            if (l >= r) {
                break;
            }
            // 交换两个数据
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == mind) {
                r--;
            }
            if (arr[r] == mind) {
                l++;
            }
        }

//            int temp = arr[l];
//            arr[l] = arr[r];
//            arr[r] = temp;
//            if (arr[l] == mind) {
//                r--;
//            }
//            if (arr[r] == mind) {
//                l++;
//            }
//        }
        if (l == r) {
            l++;
            r--;
        }
        // 左边递归调用
        if (l < right) {
            quickSort7(l,right,arr);
        }
        // 右边递归调用
        if (left < r) {
            quickSort7(left,r,arr);
        }
    }


    public static void quicksort44(int left, int right, int[] arr) {
        int l = left;
        int r = right;
        int mind = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < mind) {
                l++;
            }
            while (arr[r] > mind) {
                r--;
            }
            if (l >= r) {
                break;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == mind) {
                r--;
            }
            if (arr[r] == mind) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        // 左边递归调用
        if (l < right) {
            quicksort44(l,right,arr);
        }
        // 右边递归调用
        if (left < r) {
            quicksort44(left,r,arr);
        }
    }





    public static int partition(int[] arr,int low,int high){
        int index = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while(i < j && arr[j] > index){
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            while (i < j && arr[i] < index) {
                i++;
            }
            if(i < j){
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = index;
        return i;
    }


    public static void quickSort100(int[] arr, int low, int high) {
        if(low < high){
            int partition = partition(arr,low,high);
            quickSort100(arr,low,partition - 1);
            quickSort100(arr,partition + 1,high);
        }
    }


    public static int partition101(int[] arr,int low,int high){
        int index = arr[low];
        int i = low;
        int j = high;
        while (i < j){
            while (i < j && arr[j] > index){
                j--;
            }
            if(i < j){
                arr[i] = arr[j];
                i++;
            }
            while (i < j && arr[i] < index){
                i++;
            }
            if(i < j){
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = index;
        return i;
    }

    public static void quickSort101(int[] arr,int low,int high){
        if(low < high){
            int partition = partition101(arr,low,high);
            quickSort101(arr,low,partition - 1);
            quickSort101(arr,partition + 1,high);
        }
    }


    public static int partition102(int low, int high, int[] arr) {
        int i = low;
        int j = high;
        int index = arr[low];
        while (i < j) {
            while (i < j && arr[j] > index) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
            while (i < j && arr[i] < index) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = index;
        return i;
    }

    public static void quickSort102(int[] arr, int low, int high) {
        if (low < high) {
            int partition = partition102(low, high, arr);
            quickSort102(arr,low,partition-1);
            quickSort102(arr,partition + 1,high);
        }
    }



    public static int paritiotn103(int[] arr,int low,int high){
        int index = arr[low];
        int i = low;
        int j = high;
        while (i < j){
            while (i < j && arr[j] > index){
                j--;
            }
            if(i < j){
                arr[i++] = arr[j];
            }
            while (i < j && arr[i] < index){
                i++;
            }
            if(i < j){
                arr[j--] = arr[i];
            }
        }
        arr[i] = index;
        return i;
    }



    public static void quickSort103(int[] arr,int low,int high){
        if(low < high){
            int partition = paritiotn103(arr,low,high);
            quickSort103(arr,low,partition - 1);
            quickSort103(arr,partition + 1,high);
        }
    }


    public static int partition104(int[] arr, int low, int high) {
        int index = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (i < j && arr[j] > index) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
            while (i < j && arr[i] < index) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = index;
        return i;
    }

    public static void quickSort104(int[] arr, int low, int high) {
        if (low < high) {
            int partition = partition104(arr, low, high);
            quickSort104(arr,low,partition - 1);
            quickSort104(arr,partition + 1,high);
        }
    }


    public static int partition105(int[] arr, int low, int high) {
        // 基准数
        int index = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (i < j && arr[i] < index) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
            while (i < j && arr[j] > index) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
        }
        arr[i] = index;
        return i;
    }

    public static void quickSort105(int[] arr, int low, int high) {
        if (low < high) {
//            int partition = (low + high) / 2;
            int partition = partition105(arr,low,high);
            quickSort105(arr,low,partition);
            quickSort105(arr,partition + 1,high);
        }
    }


}
