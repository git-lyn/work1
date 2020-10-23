package com.lyn.juc.leet;

import java.util.*;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-06-30 20:13
 **/
public class LeetCode10 {
    public static void main(String[] args) {
        int[] array = {-1,1,1,2,1,7,1};
        System.out.println(removeElement(array, 1));
    }

    /**
     * 进行数据的整理操作
     *  15、Sum 三数之和
     */
    public static void threeNumSum(){
        int[] nums = {-1,1,0,2,5,7};
        // 防止出现重复数字， 使用list进行数据的存储
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0;i < nums.length - 2;i++) {
            while (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = nums[i - 1];
            int right = nums.length - 1;
            if (nums[i] + nums[left] + nums[right] == 0) {
//                result.add(Arrays.sort(nums[i],nums[left],nums[right]));
            }
        }
    }

    /*
     * 21. Merge Two Sorted Lists
     * 题意：合并两个有序链表
     * 难度：Easy
     * 分类：Linked List
     * Tips：可用递归的写法，代码更简洁
     */
    public static ListNode mergeTowLists(ListNode l1,ListNode l2){
        ListNode res = new ListNode(999);
        ListNode temp = res;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                temp.next = l1;
                temp = temp.next;
                l1 = l1.next;
            } else {
                temp.next = l2;
                temp = temp.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            temp.next = l1;
        }
        if (l2 != null) {
            temp.next = l2;
        }
        return res.next;
    }

    /*
     * 26. Remove Duplicates from Sorted Array
     * 题意：移除数组中重复元素
     * 难度：Easy
     * 分类：Array, Two Pointers
     *      lc27
     */
    public static Object[] removeDuplicates(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i < arr.length;i++) {
            if (!map.containsValue(arr[i])) {
                map.put(i, arr[i]);
            }
        }
        return map.values().toArray();
    }


    /*
     * 27. Remove Element
     * 题意：移除掉数组中指定的值，返回移除后数组的长度
     * 难度：Easy
     * 分类：Array, Two Pointers
     * 思路：两个指针，分别O(n)，指向要交换的位置和和他交换的数
     *      答案中直接遍历一遍数组，放到位置上就行了，i++
     * Tips：lc26
     */
    public static int removeElement(int[] arr, int val) {
//        int i = 0;
//        for(int j = 0;j < arr.length;j++) {
//            if (arr[j] != val) {
//                arr[i] = arr[j];
//                i++;
//            }
//        }
//        return i;
        int i = 0;
        for(int j = 0;j < arr.length;j++) {
            if (arr[j] != val) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        return i;
    }

    /*
     * 28. Implement strStr()
     * 题意：找出子串在给定字符串的起始位置
     * 难度：Easy
     * 分类：Two Pointers, String
     * Tips：注意判断子串为空的方法为needle.length()==0，不要用needle==""
     *       最优的解法应该是O(N)的，类似KMP的思路，不过面试不会让写KMP的
     */
    public static int strStr(String str, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int cur1 = 0;
        int cur2 = 0;
        while (cur1 < str.length()) {
            if (str.charAt(cur1) == needle.charAt(cur1)) {
                cur1++;
                cur2++;
                if (cur2 == needle.length()) {
                    return cur1 - cur2;
                }
            }else{
                cur1++;
                cur1 = cur1 - cur2;
                cur2 = 0;
            }
        }
        return -1;
    }


    /*
     * 29. Divide Two Integers
     * 题意：不用乘法，除法，取模运算实现除法
     * 难度：Medium
     * 分类：Math, Binary Search
     * 思路：被除数减去除数，除数每次左移一位，也就是*2 来实现类似二分的思想
     * Tips：注意下用long类型，以及溢出的情况，注意符号
     */



}

class ListNode{
    public ListNode next;
    public int data;

    public ListNode(int data) {
        this.data = data;
    }
}
