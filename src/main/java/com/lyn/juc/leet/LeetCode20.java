package com.lyn.juc.leet;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-08-28 21:31
 *
 * leetcode 30 - 60
 *
 **/
public class LeetCode20 {
    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        String s = addBinary(a, b);
        System.out.println(s);
    }

    /**
     * leetcode35: Search Insert Position
     *     找到目标数在有序数组的index
     *    [1,3,5,6] 5 -> 2
     *    [1,3,5,6] 2 -> 1
     *    [1,3,5,6] 7 -> 4
     *    [1,3,5,6] 0 -> 0
     *
     *    采用二分查找算法:  时间复杂度: log n 空间复杂度: 1
     */

    public static int searchInsert(int[] arr,int target){
        int start = 0;
        int end = arr.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (arr[start] >= target) {
            return start;
        }else if (arr[end] >= target) {
            return  end;
        }else{
            return end + 1;
        }
    }

    /**
     * 53. Maximum subarray
     *  找到数组的最大子串
     *  [1,3,-4,2]
     *  采用dp的方式
     * @param arr
     * @return
     */
    public static int maxSubArray(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int res = arr[0];
        for(int i = 1;i < arr.length;i++) {
            dp[i] = arr[i] + (dp[i - 1] < 0 ? 0 : dp[i - 1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 67. Add  Binary
     * <p>
     * a = '11'
     * b = '1'
     */
    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int remainder = 0;
        while (i >= 0 || j >= 0) {
            int sum = remainder;
            if(i >= 0) sum += a.charAt(i) - '0';
            if(j >= 0) sum += b.charAt(j) - '0';
            sb.append(sum % 2);
            remainder = sum / 2;
        }
        if (remainder != 0) {
            sb.append(remainder);
        }
        return  sb.reverse().toString();
    }



}
