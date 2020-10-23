package com.lyn.juc.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-06-11 22:09
 *  leetcode 前20道题目
 **/
public class LeetCode1 {
    public static void main(String[] args) {

    }

    /**
     * 1. Two Sum 两数之和
         Given nums = [2, 7, 11, 15], target = 9,
         Because nums[0] + nums[1] = 2 + 7 = 9,
         return [0, 1].
        对应的时间复杂度为 n
     * @return
     */
    public static int[] twoSum(int[] arr,int target){
        Map<Integer, Integer> hashMap = new HashMap<>();
        int[] result = new int[2];
        for(int i = 0;i < arr.length;i++) {
            if (hashMap.containsKey(target - arr[i])) {
                result[0] = i;
                result[1] = hashMap.get(target - arr[i]);
                break;
            }
            hashMap.put(arr[i], i);
        }
        return result;
    }

    /**
     * Add Two Numbers 两个数字相加
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     */
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int max = 0;
        int j = 0;

        return 0;
    }

}
