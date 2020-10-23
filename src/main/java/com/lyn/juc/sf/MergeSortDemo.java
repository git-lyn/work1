package com.lyn.juc.sf;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-05-18 22:09
 *  使用归并排序进行数据的排序
 *  对应的时间复杂度： O(n * log n)
 **/
public class MergeSortDemo {
    public static void main(String[] args) {

        int[] arr = {7, 65, 3, 5,5,7, 24, 99, 88, 71};
//        mergeSort4(0,arr.length - 1,arr,new int[arr.length]);
//        mergeSort4(0,arr.length - 1,arr,new int[arr.length]);
//        mergeSort103(arr,0,arr.length - 1,new int[arr.length]);
        mergeSort104(arr,new int[arr.length],0,arr.length - 1);
        for (int temp : arr) {
            System.out.print(temp + " ");
        }
        System.out.println();
    }

    /**
     * 归并排序算法：
     *  时间复杂度： O(n * log n)
     * @param left
     * @param right
     * @param arr
     */
    public static void mergeSort(int left, int right, int[] arr,int[] temp) {
        if (left < right) {
            int mind = (left + right) / 2;
            // 左边归并排序
            mergeSort(left,mind,arr,temp);
            // 右边归并排序
            mergeSort(mind + 1,right,arr,temp);
            merge(left, mind, right, arr, temp);
        }
    }

    public static void merge(int left, int mind, int right, int[] arr, int[] temp) {
        int i = left;
        int j = mind + 1;
        // 设置临时数组对应的下标index
        int t = 0;
        while (i <= mind && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                i++;
                t++;
            }else{
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        // 将左边剩余数据填充到临时数组中
        while (i <= mind) {
            temp[t++] = arr[i++];
        }
        // 将右边剩余数据填充到临时数组中
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        // 将临时数组中的数据填充到原来的数组中
        int tempIndex = left;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

    /**
     * 采用归并排序进行数据的排序
     */
    public static void mergeSort2(int left, int right, int[] arr,int[]  temp) {
//        if (left < right) {
//            int mind = (left + right) / 2;
//            // 左边递归
//            mergeSort2(left,mind,arr,temp);
//            // 右边递归
//            mergeSort2(mind + 1, right, arr,temp);
//            merge(left,right,mind,arr,temp);
//        }

        if (left < right) {
            int mind = (left + right) / 2;
            // 左边归并排序
            mergeSort2(left,mind,arr,temp);
            // 右边归并排序
            mergeSort2(mind + 1,right,arr,temp);
            merge(left, mind, right, arr, temp);
        }
    }

    public static void merge2(int left, int mind,int right, int[] arr,int[] temp) {
        int i = left;
        int j = mind + 1;
        int t = 0;
        while (i <= mind && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }
        // 将剩余的数据填充到临时数组中
        while (i <= mind) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }


    /**
     * 采用归并排序算法进行数据的排序
     */
    public static void mergeSort3(int left, int right, int[] arr, int[] temp) {
        if (left < right) {
            int mind = (left + right) / 2;
            // 左边进行归并排序
            mergeSort3(left,mind,arr,temp);
            // 右边进行归并
            mergeSort3(mind + 1,right,arr,temp);
            // 整体进行递归
            merge3(left, mind, right, arr, temp);
        }
    }

    public static void merge3(int left, int mind, int right, int[] arr, int[] temp) {
        int i = left;
        int j = mind + 1;
        int t = 0;
        while (i <= mind && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        // 将左边剩余数据填充到临时数组中
        while (i <= mind) {
            temp[t++] = arr[i++];
        }
        // 将右边剩余数据填充到临时数组中
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        // 将临时数组中的数据填充到原来的数组
        t = 0;
        // 实现相应的两个数据治的减缓
        // 机型
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }


    /**
     * 归并排序算法：进行数据的排序操作
     */
    public static void mergeSort4(int left, int right, int[] arr, int[] temp) {
        if (left < right) {
            int mind = (left + right) / 2;
            // 左边进行递归排序算法
            mergeSort4(left, mind, arr, temp);
            // 右边进行递归排序算法
            mergeSort4(mind + 1,right,arr,temp);
            merge4(left,mind,right,arr,temp);
        }
    }

    public static void merge4(int left, int mind, int right, int[] arr, int[] temp) {
        int i = left;
        int j = mind + 1;
        int t = 0;
        while (i <= mind && j <= right) {
            // 进行数据的处理
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        // 将左边剩余数据填充到临时数组中
        while (i <= mind) {
            temp[t++] = arr[i++];
        }
        // 将右边剩余数据填充到临时数组中
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        // 将临时数组中的数据填充到原来的数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }


    /**
     * 归并排序： 平均时间： n*log*n  最差时间： n*log*n
     *  分而治之： 先分后总
     *
     */
    public static void merge22(int left,int right,int[] arr,int[] temp){
        if (left < right) {
            int mind = (left + right);
            // 左边递归调用
            merge22(left,mind,arr,temp);
            // 右边递归调用
            merge22(mind + 1,right,arr,temp);
            mergeSort22(left,mind,right,arr,temp);
        }
    }

    public static void mergeSort22(int left, int mind, int right, int[] arr, int[] temp) {
        int i = left;
        int j = mind + 1;
        int t = 0;
        while (i <= mind && j <= mind) {
            if (arr[i] < arr[mind]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        if (i <= mind) {
            temp[t++] = arr[i++];
        }
        if (j >= mind) {
            temp[t++] = arr[j++];
        }
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }


    public static void merge(int[] arr,int left,int mind,int right,int[]temp){
        int t = 0;
        int i = left;
        int j = mind + 1;
        while(i <= mind && j <= right){
            if(arr[i] < arr[j]){
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }
        while (i <= mind){
            temp[t++] = arr[i++];
        }
        while (j <= right){
            temp[t++] = arr[j++];
        }
        t = 0;
        while (left <= right){
            arr[left++] = temp[t++];
        }
    }

    public static void mergeSort100(int[] arr,int left,int right,int[] temp){
        if(left < right){
            int mind = (left + right) / 2;
            mergeSort100(arr,left,mind,temp);
            mergeSort100(arr,mind + 1,right,temp);
            merge(arr,left,mind,right,temp);
        }
    }

    public static void merge101(int[] arr, int left,int mind, int right, int[] temp) {
        int i = left;
        int j = mind + 1;
        int t = 0;
        while (i <= mind && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mind) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

    public static void mergeSort101(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mind = (left + right) / 2;
            mergeSort101(arr, left, mind, temp);
            mergeSort101(arr,mind + 1,right,temp);
            merge101(arr,left,mind,right,temp);
        }
    }



    public static void merge102(int[] arr,int left,int mind,int right,int[] temp){
        int t = 0;
        int i = left;
        int j = mind + 1;
        while (i <= mind && j <= right){
            if(arr[i] < arr[j]){
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }
        while (i <= mind){
            temp[t++] = arr[i++];
        }
        while (j <= right){
            temp[t++] = arr[j++];
        }
        t = 0;
        while (left <= right){
            arr[left++] = temp[t++];
        }
    }




    public static void mergeSort102(int[] arr,int left,int right,int[] temp){
        if(left < right){
            int mind = (left + right) / 2;
            mergeSort102(arr,left,mind,temp);
            mergeSort102(arr,mind + 1,right,temp);
            merge102(arr,left,mind,right,temp);
        }
    }

    public static void merge103(int[] arr, int left, int mind, int right, int[] temp) {
        int t = 0;
        int i = left;
        int j = mind + 1;
        while (i <= mind && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mind) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

    public static void mergeSort103(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mind = (left + right) / 2;
            mergeSort103(arr,left,mind,temp);
            mergeSort103(arr,mind + 1,right,temp);
            merge103(arr, left, mind, right, temp);
        }
    }


    public static void mergeSort104(int[] arr,int[] temp, int left, int right) {
        if (left < right) {
            int mind = (left + right) / 2;
            mergeSort104(arr,temp,left,mind - 1);
            mergeSort104(arr,temp,mind + 1,right);
            merge104(arr,temp,left,mind,right);
        }
    }

    public static void merge104(int[] arr,int[] temp, int left, int mind, int right) {
        int i = left;
        int j = mind + 1;
        int t = 0;
        while (i <= mind && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mind) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }


    }


}
