package com.lyn.juc.leet.leetcode;

import java.util.*;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-01-31 16:55
 **/
public class LeetCode21to40 {
    public static void main(String[] args) {

    }

    /**
     * 23. Merge k sorted List
     * 合并k个list有序链表
     * <p>
     * time: o(nlogk)
     * space: o(n)
     *
     * @param lists
     * @return
     */
    public static ListNode leet23MergeKList(ListNode[] lists) {
        ListNode res = new ListNode(0);
        ListNode cur = res;
        // 创建队列
        /**
         *
         new Comparator<ListNode>() {
        @Override public int compare(ListNode o1, ListNode o2) {
        return o1.data - o2.data;
        }
         */
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.data - b.data);
        for (int i = 0; i < lists.length; i++) {
            queue.add(lists[i]);
        }
        while (!queue.isEmpty()) {
            // 弹出队列头部的数据
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null) {
                queue.add(cur.next);
            }
        }
        return res.next;
    }

    /**
     * 24. Swap Nodes in Pairs
     * 翻转链表的两个节点
     * <p>
     * time: o(n)
     * space: o(1)
     * 1 -> 2 -> 3 -> 5
     * 2 -> 1 -> 5 -> 3
     *
     * @param node
     * @return
     */
    public static ListNode leet24SwapPairs(ListNode node) {
        if (node == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = node;
        ListNode l1 = dummy;
        ListNode l2 = node;
        while (l2 != null && l2.next != null) {
            // 进行节点的转换
            ListNode nexts = l2.next.next;
            l1.next = l2.next;
            l2.next.next = l2;
            l2.next = nexts;
            l1 = l2;
            l2 = l2.next;
        }
        return dummy.next;
    }

    /**
     * 26. remove dumplicate from sorted array
     * time: o(n)
     *
     * @param arr
     * @return
     */
    public static int leet26RemoveDuplicateArray(int[] arr) {
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == arr[i]) {
                arr[count++] = arr[i];
            }
        }
        return count;
    }

    /**
     * 28. Remove Element:
     * 删除数组中的指定的元素
     * time: o(n)
     * space: o(1)
     *
     * @param arr
     * @param target
     * @return
     */
    public static int leet27RemoveElement(int[] arr, int target) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != target) {
                arr[count++] = arr[i];
            }
        }
        return count;
    }

    /**
     * 28. 返回字符串存在的 开始位置
     * time: o(n)
     *
     * @param ori
     * @param target
     * @return
     */
    public static int leet28strStr(String ori, String target) {
        if (ori == null || ori.length() == 0) return 0;
        for (int i = 0; i < (ori.length() - target.length()); i++) {
            if (ori.substring(i, i + target.length()).equals(target)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 29. divide
     * 不使用 /  %  等，实现 除法操作
     *
     * @param high
     * @param low
     * @return
     */
    public static int leet29Divide(int high, int low) {
        // 符号的标志位
        int sign = 1;
        if ((high < 0 && low > 0) || (high > 0 && low < 0))
            sign = -1;
        long lhigh = Math.abs((long) high);
        long llow = Math.abs((long) low);
        if (lhigh < low || lhigh == 0) return 0;
        long lres = divide(lhigh, llow);
        int res = 0;
        if (lres > Integer.MAX_VALUE) {
            res = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else
            res = (int) (sign * res);
        return res;
    }

    public static long divide(long high, long low) {
        if (high < low) return 0;
        int multiple = 1;
        long sum = low;
        while ((sum + sum) < high) {
            sum += sum;
            multiple += multiple;
        }
        return multiple + divide(high - sum, low);
    }

    /**
     * 32. Longest Valid Parentthese: 得到最长的匹配小括号的长度:()
     * <p>
     * ()   length 2
     * ()()   length 4
     * ()匹配的问题，采用stack栈来处理  ()()   (())
     * (()(()
     *
     * @param s
     * @return
     */
    public static int leet32LongestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        // 正常的起点位置
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    start = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        res = Math.max(res, i - start);
                    } else { // 用来处理  (())  这种情况
                        res = Math.max(res, i - stack.peek());
                    }
                }
            }
        }
        return res;
    }

    /**
     * 33. search in Rotated Sorted Array
     * 从翻转的有序数组中，找到指定的数       4 5 6 7 0 1 3
     * 有序数组： 使用二分查找进行处理        4 5 6 0 1 2 3
     * time: o(logn)
     * space:o(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int leet33Search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mind = (start + end) / 2;
            if (nums[mind] == target) return mind;
            if (nums[mind] > target) {
                if (nums[start] <= target && target <= nums[mind]) {
                    end = mind;
                } else {
                    start = mind;
                }
            } else {
                if (nums[mind] <= target && target <= nums[end]) {
                    start = mind;
                } else {
                    end = mind;
                }
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }

    /**
     * 34. Serch for a Range
     * 查找target  在数组中出现的第一次和最后一次
     * <p>
     * [5, 7, 7, 8, 8, 10]   target = 8  result [3, 4]
     * <p>
     * time o(logn)
     * space o(1)
     *
     * @param num
     * @param target
     * @return
     */
    public static int[] leet34SearchRange(int[] num, int target) {
        if (num == null || num.length == 0) return new int[]{-1, -1};
        int first = first(num, target);
        int end = end(num, target);
        return new int[]{first, end};
    }

    private static int end(int[] num, int target) {
        int start = 0;
        int end = num.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (num[mid] == target) return mid;
            if (num[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (num[end] == target) return end;
        if (num[start] == target) return start;
        return -1;
    }

    private static int first(int[] num, int target) {
        int start = 0;
        int end = num.length - 1;
        while (start < end) {
            int mind = (start + end) / 2;
            if (num[mind] == target) return mind;
            if (num[mind] < target) {
                start = mind;
            } else {
                end = mind;
            }
        }
        if (num[start] == target) return start;
        if (num[end] == target) return end;
        return -1;
    }

    /**
     * 35. Search for Insert
     * <p>
     * 从数组中查找指定的数并返回index，如果不存在就插入到指定的index
     * <p>
     * [1,3,5,6] 5 -> 2
     * [1,3,5,6] 2 -> 1
     * [1,3,5,6] 7 -> 4
     * [1,3,5,6] 0 -> 0
     *
     * @param arr
     * @param target
     * @return
     */
    public static int leet35SearchInsert(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mind = (start + end) / 2;
            if (arr[mind] == target) return mind;
            if (arr[mind] > target) {
                end = mind;
            } else {
                start = mind;
            }
        }
        if (target <= arr[start]) {
            return start;
        } else if (target >= arr[end]) {
            return end;
        } else {
            return end + 1;
        }
    }

    /**
     * 36. valid  Sudoku
     * 判断二维数组是否是数独
     *
     * @param board
     * @return
     */
    public static boolean leet36ValidSudoku(char[][] board) {
        if(board == null || board.length == 0) return false;
        for(int i = 0;i < board.length;i++) {
            Set<Character> row = new HashSet<>();
            Set<Character> col = new HashSet<>();
            Set<Character> cube = new HashSet<>();
            for(int j = 0;j < board[0].length;j++) {
                // 数独一横行
                if(board[i][j] != '.' && !row.add(board[i][j])) return false;
                // 数独一列
                if(board[j][i] != '.' && !row.add(board[j][i])) return false;
                // 验证3 * 3 格数据是否重复
                int rowIndex = 3 * (i / 3);
                int colIndex = 3 * (i % 3);
                if(board[rowIndex + j / 3][colIndex + j % 3] != '.' && !cube.add(board[rowIndex + j / 3][colIndex + j % 3]))
                    return false;
            }
        }
        return true;
    }

    /**
     *   39.  combination sum
     *       判断数组中的数相加的和为  target
     *  time: o(2^n)
     * @param arr
     * @param target
     * @return
     */
    public static List<List<Integer>> leet39CombinationSum(int[] arr, int target) {
        if(arr == null || arr.length == 0) return null;
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<Integer>(), arr,target, 0);
        return res;
    }

    private static void helper(List<List<Integer>> res, ArrayList<Integer> list, int[] arr, int target, int start) {
        if(target < 0 ) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0;i <  arr.length;i++) {
            list.add(arr[i]);
            helper(res,list,arr,target - arr[i],i);
            list.remove(list.size() - 1);
        }
    }


}
