package com.lyn.juc.leet.leetcode;

import scala.Char;
import scala.Predef;

import java.util.*;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-04-20 08:33
 **/
public class Leetcode31to50 {
    public static void main(String[] args) {
        // [1, 5, 8, 5, 1, 3, 4, 6, 7]
//        int[] arr = {1,5,8,4,7,6,5,3,1};
//        nextPerMutation(arr);
        String str = "()(()";
        longestParentheses32(str);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);
        ;
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE - 1);

    }

    /**
     * 31. Next Permutation
     * 题意: 找出排列组合的下一个排序
     */

    public static void nextPerMutation(int[] arr) {
        int length = arr.length;
        int index = length - 1;
        for (int i = index; i > 0; i--) {
        }
        while (arr[index - 1] >= 0 && arr[index - 1] > arr[index]) {
            index--;
        }
        int big = length - 1;
        while (big >= index && arr[big] < arr[index - 1]) {
            big--;
        }
        int temp = arr[index - 1];
        arr[index - 1] = arr[big];
        arr[big] = temp;

        int i = index;
        int j = arr.length - 1;
        while (i < j) {
            int temp2 = arr[i];
            arr[i] = arr[j];
            arr[j] = temp2;
            i++;
            j--;
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 32. Longest Valid Parentheses 最长有效括号
     * <p>
     * Example 1:
     * <p>
     * Input: "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()"
     * Example 2:
     * <p>
     * Input: ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()"
     */
    // 使用栈的方式还有dp
    public static void longestParentheses32(String str) {
        // 采用栈的方式进行处理   ()(())
        Stack<Integer> stack = new Stack<Integer>();
        int res = 0;
        // 先向栈中存储一个数据
        stack.push(-1);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') { // 将( 放入栈中
                stack.push(i);
            } else if (c == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // 保证结尾处
                }
                res = Math.max(res, i - stack.peek());
            }
        }

        System.out.println("当前最长括号的长度为: " + res);

    }


    public static void longestParentheses322(String str) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else if (str.charAt(i) == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                res = Math.max(res, i - stack.peek());
            }
        }
    }


    /**
     * 33. Search in Rotated Sorted Array 在旋转有序数组中搜索
     * [LeetCode] 33. Search in Rotated Sorted Array 在旋转有序数组中搜索
     * <p>
     * <p>
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     * <p>
     * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
     * <p>
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     * <p>
     * You may assume no duplicate exists in the array.
     * <p>
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     * Example 2:
     * <p>
     * Input: nums = [4,5,6,7,0,1,2], target = 3
     * Output: -1
     */

    public static int search33(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mind = (left + right) / 2;
            if (arr[mind] == target) {
                return mind;
            }
            if (arr[mind] < arr[right]) { // 右边有序
                if (arr[mind] < target && arr[right] >= target)
                    left = mind + 1;
                else
                    right = mind - 1;
            } else {
                if (arr[left] <= target && arr[mind] > target)
                    right = mind - 1;
                else
                    left = mind + 1;
            }
        }
        return -1;
    }


    public static int search33_2(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mind = (left + right) / 2;
            if (arr[mind] == target) return mind;
            if (arr[mind] < arr[right]) {// 右边有序
                if (arr[mind] < target && arr[right] >= target)
                    left = mind + 1;
                else
                    right = mind - 1;
            } else {// 左边有序
                if (arr[left] <= target && arr[mind] > target) {
                    right = mind - 1;
                } else {
                    left = mind + 1;
                }
            }
        }
        return -1;
    }


    /**
     * [LeetCode] 34. Find First and Last Position of Element in Sorted Array
     * 在有序数组中查找元素的第一个和最后一个位置
     * <p>
     * <p>
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
     * <p>
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * <p>
     * If the target is not found in the array, return [-1, -1].
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     * Example 2:
     * <p>
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     */
    // 使用两次二分查找算法
    public static int[] searchRange34(int[] arr, int target) {
        int res[] = {-1, -1};
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mind = (start + end) / 2;
            if (arr[mind] < target) {
                start = mind + 1;
            } else {
                end = mind;
            }
        }
        if (arr[start] != target) {
            return res;
        }
        res[0] = start;
        end = arr.length - 1;
        while (start < end) {
            int mind = (start + end) / 2 + 1;
            if (arr[mind] > target) {
                end = mind - 1;
            } else {
                start = mind;
            }
        }
        if (arr[start] != target) {
            return res;
        }
        res[1] = start;
        return res;
    }

    /**
     * [LeetCode] 35. Search Insert Position 搜索插入位置
     * <p>
     * <p>
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     * <p>
     * You may assume no duplicates in the array.
     * <p>
     * Example 1:
     * <p>
     * Input: [1,3,5,6], 5
     * Output: 2
     * Example 2:
     * <p>
     * Input: [1,3,5,6], 2
     * Output: 1
     * Example 3:
     * <p>
     * Input: [1,3,5,6], 7
     * Output: 4
     * Example 4:
     * <p>
     * Input: [1,3,5,6], 0
     * Output: 0
     */

    public int searchInsert35(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mind = (left + right) / 2;
            if (arr[mind] == target) return left;
            if (arr[mind] > target) {
                right = mind - 1;
            } else {
                left = mind;
            }
        }
        return left;
    }

    /**
     * 求解: 最长递增子序列
     * 时间复杂度: O(n * n)
     *
     * @param arr
     * @return
     */
    public static int longestIncrementSubString(int[] arr) {
        int[] dp = new int[arr.length];
        // 初始化dp数组
        for (int i = 0; i < dp.length; i++)
            dp[i] = 1;

        // 采用数学归纳法的dp
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(dp[i], res);
        }
        return res;
    }


    /**
     * 使用回溯算法处理全排列问题
     */
    private static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> quanpailie(int[] nums) {
        List<Integer> track = new ArrayList<>();
        backtrack(track, nums);
        return res;
    }

    public static void backtrack(List<Integer> track, int[] nums) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            // 进行下一个递归树
            backtrack(track, nums);
            // 取消选择
            track.remove(nums[i]);
        }
    }

    /**
     * 使用回溯算法处理8皇后问题
     */
    private static List<List<String>> queen = new ArrayList<>();

    public static List<List<String>> solveQueen(int n) {
        List<String> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }

        trackQueen(board, 0);

        return queen;
    }

    public static void trackQueen(char[][] board, int row) {
        int n = board.length;
        if (row == board.length) {
            queen.add(charToString(board));
//            queen.add(res);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            board[row][col] = 'Q';
            // 进入下一个递归树
            trackQueen(board, row + 1);
            // 取消选择
            board[row][col] = '.';

        }

//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                if (!isValid(board, row, col)) {
//                    continue;
//                }
//                board[row][col] = 'Q';
//                // 进入下一个递归树
//                trackQueen(board,res,row + 1);
//                // 取消选择
//                board[row][col] = '.';
//            }
//        }

    }

    /**
     * I
     * 校验8皇后的 同一列、左上角、右上角是否有皇后
     *
     * @param board
     * @return
     */
    public static boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        // 判断同一列是否有皇后
        for (int i = 0; i <= col; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        // 判断左上角是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }

        // 判断右上角是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }

        return true;
    }

    public static List<String> charToString(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] chars : board) {
            res.add(new String(chars));
        }
        return res;
    }


    public static int fb(int n) {
        if (n == 1 || n == 2)
            return 1;

        int[] dp = new int[n];
        if (n >= 3) {
            for (int i = 3; i < n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[n - 1];
    }

    /**
     * 凑零钱问题:
     * 先看下题目：给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，
     * 再给一个总金额 amount，问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1
     */
    public static int coins(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // base case: 默认都使用1元进行填充
        for (int i = 0; i < dp.length; i++) {
            dp[i] = amount + 1;
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (dp[i] - coins[j] > 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount - 1];
    }

    /**
     * 最长递增子序列
     */

    public static int longestIncrementSubsequence(int[] nums) {
        int[] dp = new int[nums.length + 1];
        // 至少有一个数
        Arrays.fill(dp, 1);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length];
    }

    /**
     * 编辑距离:
     * 字符串s1，  字符串s2
     * 将字符串s1 变成s2，
     * 只能进行  增加、删除、修改  三种操作
     */

    public static int minDisntace(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case操作
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 三种操作: 增加、删除、修改
                    dp[i][j] = Math.min(
                            dp[i][j - 1] + 1,
                            Math.min(
                                    dp[i - 1][j] + 1,
                                    dp[i - 1][j - 1] + 1
                            )
                    );
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 贪心算法之区间调度问题
     * 给你很多形如 [start, end] 的闭区间，请你设计一个算法，算出这些区间中最多有几个互不相交的区间。
     */
    public static int intervalSchedule(int[][] nums){
        // 先进行排序操作,每个数组的end 排序
        Arrays.sort(nums,(a,b) ->{
            return a[1] - a[1];
        });
        int  n = nums.length;
        int temp = nums[0][1];
        int res = 1;
        for (int i = 0; i < n; i++) {
            // 数组的start 大于 第一个值的end
            if (nums[i][0] >= temp) {
                res++;
                temp = nums[i][1];
            }
        }
        return res;
    }

    /**
     * 滑动窗口: 最小覆盖字串
     */
    public static String minStr(String s, String t) {
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();
        int left = 0;
        int right = 0;
        int len = Integer.MAX_VALUE;
        int start = 0;
        int match = 0;
        for(int i = 0;i < t.length();i++)
            needs.put(t.charAt(i),needs.getOrDefault(t.charAt(i),0) + 1);

        while (right < s.length()) {
            char c = s.charAt(right);
            if (needs.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c) == needs.get(c)) {
                    match++;
                }
            }
            right++;

            while (match == needs.size()) {
                char d = s.charAt(left);
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                left++;
                if (needs.containsKey(d)) {
                    windows.put(d, windows.get(d) - 1);
                    if (windows.get(d) < needs.get(d)) {
                        match--;
                    }
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start,start + len);
    }

    /**
     * 无重复字符的最长子串
     * 滑动窗口进行处理
     */
    public static int longestStr(String s) {
        int n = s.length();
        Map<Character, Integer> windows = new HashMap<>();
        int left = 0;
        int right = 0;
        int start = 0;
        int math = 0;
        while (right < n) {
            char c = s.charAt(right);
            windows.put(c, windows.getOrDefault(c, 0) + 1);
            right++;
            while (windows.get(c) > 1) {
                char d = s.charAt(left);
                windows.put(d, windows.get(d) - 1);
                left++;
            }
            start = left;
            math = right - left;
        }
        return math;
    }


    /**
     *
     * 最长公共子序列: dp处理
     * 输入: str1 = "abcde", str2 = "ace"
     * 输出: 3
     * 解释: 最长公共子序列是 "ace"，它的长度是 3
     *
     */
    public static int longestCommonSequence(String s1,String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}


/**
 * 设计LRU缓存
 */

class LRUnode{
    public int key;
    public String value;
    public LRUnode prev;
    public LRUnode next;

    public LRUnode(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

class LRUDoubleList{
    private LRUnode root;
    public LRUDoubleList(){
        //this.root = new LRUnode(-1, "-1");
    }

    // 在链表头部添加节点
    public void addFirst(LRUnode node) {
        if (root == null) {
            root = node;
        } else {
            root.prev = node;
            node.next = root;
        }
    }
    // 删除链表中的节点
    public void remove(LRUnode node){
        if(root == null)
            return;
        while (root != null) {
            if (node.key == root.key) {
                root.next.prev = null;
                root = root.next;
                return;
            } else {
                root = root.next;
            }
        }
    }

    // 删除链表中最后一个节点，并且返回该节点
    public LRUnode removeLast() {
        if (root == null) {
            return null;
        }
        LRUnode current = root;
        while (current != null) {
            if (current.next == null) {
                LRUnode node = current;
                current.prev.next = null;
                return node;
            }
        }
        return null;
    }

    // 返回链表长度
    public int size(){
        LRUnode dummy = root;
        int len = 0;
        if(dummy == null)
            return len;
        while (dummy != null) {
            len++;
            dummy = dummy.next;
        }
        return len;
    }
}

/**
 * 建立LRU缓存
 */

class LRUCache{
    private Map<Integer,LRUnode> map;
    private LRUDoubleList doubleList;
    private int cap = 10;
    public LRUCache(int capacity){
        cap = capacity;
        doubleList = new LRUDoubleList();
    }

    public String get(int key) {
        if (!map.containsKey(key)) {
            return null;
        }
        LRUnode lrUnode = map.get(key);
        String value = lrUnode.value;
        // 利用put将数据放到链表的头部
        put(key, value);
        return value;
    }

    public void put(int key, String value) {
        // 判断map中是否存在该数据
        LRUnode newNode = new LRUnode(key, value);
        if (map.containsKey(key)) {
            // 删除旧的节点
            doubleList.remove(newNode);
            doubleList.addFirst(newNode);
            // 替换数据
            map.put(key, newNode);
        }else{
            if (cap == doubleList.size()) {
                // 删除链表最后一个节点
                LRUnode node = doubleList.removeLast();
                map.remove(node);
            }
            // 直接添加到头部
            doubleList.addFirst(newNode);
            map.put(key,newNode);
        }
    }


    /**
     * 0-1背包问题处理
     */
    public static int packet(int target) {
        int[] weight = {1, 2, 5};
        int[] values = {20, 40, 50};
        int[][] dp = new int[values.length + 1][target + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 分成放入和不放入两种状态
                if (target - weight[i - 1] > 0) {
                    // 将物品放入到背包中
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + values[i - 1]);
                } else {
                    // 物品太重，放不到背包中
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[weight.length][target];
    }

    /**
     * 筹零钱问题： 使用最少的硬币 进行处理
     */
    public static int minCoins(int target) {
        int[] coins = {1, 2, 5};
        int[] dp = new int[target + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (target - coins[j] > 0) {
                    dp[i] = Math.min(dp[i - 1], dp[i - coins[j]]);
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[target];
    }

    /**
     * 无限背包的问题:
     * 和筹零钱一样: 最少的硬币数，硬币无限
     */
    public static int maxVal(int target) {
        int[] weight = {1, 2, 5};
        int[] values = {12, 25, 40};
        int[] dp = new int[target + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < weight.length; j++) {
                if (target - weight[j] > 0) {
                    dp[i] = Math.max(dp[i - 1],dp[i - weight[j]] + values[j]);
                }else{
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[dp.length - 1];
    }

}
