package com.lyn.juc.leet.leetcode;

import javax.validation.constraints.Max;
import java.util.*;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-16 13:45
 **/
public class LeetCode1to20 {
    public static void main(String[] args) {
        String str = "adbccba";
        String s = leet5LongestPalindrome2(str);
        System.out.println(s);
    }

    /**
     * 1. Tow Sum
     * <p>
     * Given nums = [2,7,11,15], target = 9,
     * Because nums[0] + nums[1] = 2 + 7 = 9.
     * return [0,1]
     */
    public static int[] leet1(int[] arr, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(target - arr[i])) {
                res[0] = hashMap.get(arr[i]);
                res[1] = i;
            }
            hashMap.put(arr[i], i);
        }
        return res;
    }

    /**
     * 2. Add two numbers
     * <p>
     * Input ( 2 ->4->3) + (5->6->4)
     * Output:  7->0->8
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode leet2AddTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        int sum = 0;
        ListNode temp = new ListNode(0);
        ListNode p1 = l1, p2 = l2;
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                sum += p1.data;
            }
            if (p2 != null) {
                sum += p2.data;
            }
            temp.next = new ListNode(sum % 10);
            // 进行除法处理
            sum /= 10;
            temp = temp.next;
        }
        if (sum == 1) {
            temp.next = new ListNode(1);
        }
        return temp.next;
    }

    /**
     * 3. Longest Substring Without Repeating Characters
     * <p>
     * Given abcabcbb   is abc   which the length is 3
     * <p>
     * time: o(n)
     * space: o(n)
     *
     * @param s
     * @return
     */
    public static int leet3LengthOfLongestSubstring(String s) {
        int j = 0;
        int len = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            len = Math.max(len, i + 1 - j);
        }
        return len;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int len = 0;
        // j 代表的是不重复的子串的起点
        int j = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(j++));
            } else {
                set.add(s.charAt(i));
                len = Math.max(len, set.size());
            }
        }
        return len;
    }

    /**
     * 5. 求最大回文数: Longest Palindromic Substring abccba
     * dp
     * <p>
     * time: O(n * n)
     * space: O(n)
     *
     * @param s
     * @return
     */

    public static String leet5LongestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return null;
        String res = "";
        int max = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i <= 2) || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        System.out.println(max);
        return res;
    }

    /**
     * 求最大回文数:
     * dp算法
     */
    public static String leet5LongestPalindrome2(String str) {
        if (str == null || str.length() == 0)
            return null;
        String res = "";
        boolean[][] dp = new boolean[str.length()][str.length()];
        int max = 0;
        for (int j = 0; j < str.length(); j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = str.charAt(i) == str.charAt(j) && ((j - i <= 2) || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        res = str.substring(i, j + 1);
                    }
                }
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * 7. Reverse Integer
     * reverse digits of an integer
     * 翻转一个数字
     * e  x = 123, return 321
     * x = -123, return -321
     *
     * @param x
     * @return
     */
    public static int leet7Reverse(int x) {
        // 判断是否越界
        long res = 0;
        while (x > 0) {
            res = res * 10 + x % 10;
            x /= 10;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) res;
    }

    /**
     * 8. String to Integer
     * 字符串转换成Integer
     *
     * @param str
     * @return
     */
    public static int leet8StringToInteger(String str) {
        if (str == null || str.length() == 0)
            return 0;
        // 设置字符串的标志位
        int sign = 1;
        int start = 0;
        long res = 0;
        // 判断标志位
        char flag = str.charAt(0);
        if (flag == '+') {
            sign = 1;
            start++;
        } else if (flag == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i < str.length(); i++) {
            // 判断字符串是否是数字
            if (!Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            res = res * 10 + str.charAt(i) - '0';
            if (sign > 0 && res > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (sign < 0 && res < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return sign * (int) res;
    }

    /**
     * 9. 判断整数是否是回文数
     *
     * @param x
     * @return
     */
    public static boolean leet9IsPalindrome(int x) {
        // 10000
        if (x <= 0 && x % 10 == 0)
            return false;
        int res = 0;
        int copy = x;
        while (x > 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return copy == res;
    }

    /**
     * 11. ContainerWithMastWater  数组存放的最多的水
     * time: o(n)
     *
     * @param arr
     * @return
     */
    public static int leet11ContainerWithMostWater(int[] arr) {
        int res = 0;
        int l = 0, r = arr.length - 1;
        while (l < r) {
            res = Math.max(res, Math.min(arr[l], arr[r]) * (r - l));
            if (l < r) {
                l++;
            } else
                r--;
        }
        return res;
    }

    /**
     * 15. 3num: 求数组中三个数的和为0
     * time: o(n * n)
     * [-1, 0, 1, 2, -1, -4]
     *
     * @param arr
     * @return
     */

    public static List<List<Integer>> leet15ThreeNum(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        // 数组排序
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i - 1] == arr[i])
                continue;
            int low = i + 1, high = arr.length - 1, sum = 0 - arr[i];
            while (low < high) {
                if (arr[low] + arr[high] == sum) {
                    res.add(Arrays.asList(arr[i], arr[low], arr[high]));
                    while (low < high && arr[low + 1] == arr[low]) low++;
                    while (low < high && arr[high - 1] == arr[high]) high--;
                    low++;
                    high--;
                } else if (arr[low] + arr[high] > sum) {
                    high--;
                } else {
                    low++;
                }
            }
        }
        return res;
    }

    /**
     * 16. 3num closed:  三个数相加，最接近规定的target
     *
     * @param arr
     * @param target
     * @return
     */
    public static int leet16ThreeClosed(int[] arr, int target) {
        int res = arr[0] + arr[1] + arr[arr.length - 1];
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int start = i + 1, end = arr.length - 1;
            while (start < end) {
                int sum = arr[i] + arr[start] + arr[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
            }
        }
        return res;
    }

    /**
     * 19. Remove Nth Node From End of List
     * 删除倒数第n个节点    1 -> 2 -> 3 -> 4 -> 5
     *
     * @param node
     * @return
     */
    public static ListNode leet19RemoveNthNode(ListNode node, int num) {
        ListNode dummy = node;
        ListNode fast = node;
        ListNode slow = node;
        for (int i = 0; i < num; i++) {
            node = node.next;
        }
        node.next = node.next.next;

        //  使用快慢指针
        for (int i = 0; i < num; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy;
    }

    /**
     * 20. isValid   (){}[]
     * time: o(n)
     *
     * @param str
     * @return
     */
    public static boolean leet20IsValid(String str) {
        if (str == null || str.length() == 0) return false;
        // 使用 stack 栈来处理
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(')');
            } else if (str.charAt(i) == '[') {
                stack.push(']');
            } else if (str.charAt(i) == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() == str.charAt(i)) {
                    return false;
                }
            }
        }
        // 栈如果为空，就说明该字符串是匹配的，否则不是匹配的
        return stack.isEmpty();
    }

    /**
     * 21. merge two List
     *  合并两个有序的链表
     * time: o(n)
     * space: o(1)
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode leet21MergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                cur.next = new ListNode(l1.data);
                l1 = l1.next;
            } else {
                cur.next = new ListNode(l2.data);
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }


}

class ListNode {
    int data;
    ListNode next;

    public ListNode(int data) {
        this.data = data;
    }
}
