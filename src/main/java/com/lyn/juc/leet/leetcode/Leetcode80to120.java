package com.lyn.juc.leet.leetcode;

import org.omg.PortableServer.POAPackage.WrongPolicy;

import java.util.*;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-07-10 22:59
 **/
public class Leetcode80to120 {
    public static void main(String[] args) {

    }

    /**
     * [LeetCode] 81. Search in Rotated Sorted Array II 在旋转有序数组中搜索之二
     * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
     * <p>
     * You are given a target value to search. If found in the array return true, otherwise return false.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [2,5,6,0,0,1,2], target = 0
     * Output: true
     * Example 2:
     * <p>
     * Input: nums = [2,5,6,0,0,1,2], target = 3
     * Output: false
     *
     * @param num
     * @return
     */
    // 可以重复:  二分搜索
    // 遇到重复的，直接right--
    public static boolean lc81SearchRotated2(int[] num, int target) {
        int left = 0;
        int right = num.length - 1;
        while (left <= right) {
            int mind = left + (right - left) / 2;
            if (num[mind] == target) {
                return true;
            }
            if (num[mind] < num[right]) {
                if (num[mind] < target && num[right] >= target) {
                    left = mind + 1;
                } else {
                    right = mind - 1;
                }
            } else if (num[mind] > num[right]) {
                if (num[left] < target && num[mind] >= target) {
                    right = mind - 1;
                } else {
                    left = mind + 1;
                }
            } else {
                right--;
            }
        }
        return false;
    }

    /**
     * [LeetCode] 83. Remove Duplicates from Sorted List 移除有序链表中的重复项
     * Example 1:
     * <p>
     * Input: 1->1->2
     * Output: 1->2
     * Example 2:
     * <p>
     * Input: 1->1->2->3->3
     * Output: 1->2->3
     *
     * @param head
     * @return
     */
    // 和移除有序数组中重复项一样
    // 另一种是判断当前节点和下一个节点是否相同，相同就直接指向下一个节点
    public static ListNode lc83RemoveDuplicateList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode cur = head;
        ListNode slow = head;
        ListNode fast = head;

        // 快指针先走一步
        fast = fast.next;
        ListNode fa = slow;
        ListNode res = slow;
        while (fast != null) {
            if (fast.data != slow.data) {
                fa.data = fast.data;
                slow = slow.next;
            }
            fa = fa.next;
            fast = fast.next;
        }
        slow.next = null;
        return res;
    }

    /**
     * [LeetCode] 90. Subsets II 子集合之二
     * For example,
     * If S = [1,2,2], a solution is:
     * <p>
     * [
     * [2],
     * [1],
     * [1,2,2],
     * [2,2],
     * [1,2],
     * []
     * ]
     *
     * @param num
     * @return
     */
    // 回溯算法
    public static List<List<Integer>> lc90Subsets(int[] num) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        subset2Track(num, 0, track, res);
        return res;
    }

    public static void subset2Track(int[] num, int k, List<Integer> track, List<List<Integer>> res) {
        res.add(new ArrayList<>(track));
        // 路径、选择列表、结束条件
        for (int i = k; i < num.length; i++) {
            track.add(num[i]);
            // 进行下一个
            subset2Track(num, i + 1, track, res);
            // 取消选择
            track.remove(track.size() - 1);
            while (i + 1 < num.length && num[i + 1] == num[i])
                i++;
        }
    }

    /**
     * [LeetCode] 91. Decode Ways 解码方法
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     * <p>
     * Example 1:
     * <p>
     * Input: "12"
     * Output: 2
     * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     * Example 2:
     * <p>
     * Input: "226"
     * Output: 3
     * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     *
     * @param s
     * @return
     */
    // 和爬楼去类似, 使用dp进行处理
    public static int lc91DecodeWays(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        int ch = s.charAt(0) - '0';
        if (ch < 1 || ch > 9)
            return 0;
        else
            dp[1] = 1;
        for (int i = 2; i < s.length() + 1; i++) {
            int a = s.charAt(i - 2) - '0';
            int b = s.charAt(i - 1) - '0';
            if (a * 10 + b <= 26 && (a * 10 + b >= 10)) {
                dp[i] += dp[i - 2];
            }
            if (b > 0 && b <= 9) {
                dp[i] += dp[i - 1];
            }
            if (dp[i] == 0)
                return 0;
        }
        return dp[s.length()];
    }

    /**
     * [LeetCode] 92. Reverse Linked List II 倒置链表之二
     * Reverse a linked list from position m to n. Do it in one-pass.
     * <p>
     * Note: 1 ≤ m ≤ n ≤ length of list.
     * <p>
     * Example:
     * <p>
     * Input: 1->2->3->4->5->NULL, m = 2, n = 4
     * Output: 1->4->3->2->5->NULL
     *
     * @param head
     * @return
     */
    public static ListNode lc92ReverseList2(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode last = lc92ReverseList2(head.next);
        head.next.next = head;
        head.next = null;

        // 非递归的方式进行翻转链表
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return last;
    }

    /**
     * [LeetCode] Restore IP Addresses 复原IP地址
     * Example:
     * <p>
     * Input: "25525511135"
     * Output: ["255.255.11.135", "255.255.111.35"]
     *
     * @param s
     * @return
     */
    public static List<String> lc93RestoreIP(String s) {
        List<String> res = new ArrayList<>();
        restoreTrack(s, 0, "", res);
        return res;
    }

    public static void restoreTrack(String s, int k, String out, List<String> res) {
        if (k == 4) {
            res.add(out);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (k > s.length())
                break;
            int val = Integer.parseInt(s.substring(0, i));
            if (val > 255 || i != String.valueOf(val).length())
                continue;
            restoreTrack(s.substring(i), k + 1, out + s.substring(0, i) + (k == 3 ? "" : "."), res);
        }
    }

    /**
     * [LeetCode] 94. Binary Tree Inorder Traversal 二叉树的中序遍历
     * Example:
     * <p>
     * Input: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * <p>
     * Output: [1,3,2]
     *
     * @param root
     * @return
     */
    // 递归的方式:
    // 非递归的方式: 采用栈进行处理
    public static void lc94BinaryTreeInorder(Node root) {
        if (root == null) {
            return;
        }
        lc94BinaryTreeInorder(root.leftChild);
        System.out.println(root.data);
        lc94BinaryTreeInorder(root.rightChild);

        // 非递归的方式: 使用栈
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.leftChild;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                System.out.println(cur.data);
                cur = cur.rightChild;
            }
        }

    }

    /**
     * [LeetCode] 95. Unique Binary Search Trees II 独一无二的二叉搜索树之二
     * Example:
     * <p>
     * Input: 3
     * Output:
     * [
     * [1,null,3,2],
     * [3,2,null,1],
     * [3,1,null,null,2],
     * [2,1,3],
     * [1,null,2,null,3]
     * ]
     * Explanation:
     * The above output corresponds to the 5 unique BST's shown below:
     * <p>
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     *
     * @param n
     * @return
     */
    // 找到所有路径的组合方式： 使用分治算法
    public static List<Node> lc95SearchTreesAll(int n) {
        if (n > 1) {
            return null;
        }
        List<Node> list = searchTreesTrack(1, n);
        return list;
    }

    public static List<Node> searchTreesTrack(int start, int end) {
        List<Node> res = new ArrayList<>();
        if (start < end)
            return null;
        // 采用分治算法
        for (int i = start; i <= end; i++) {
            List<Node> left = searchTreesTrack(start, i - 1);
            List<Node> right = searchTreesTrack(i + 1, end);
            for (Node node : left) {
                for (Node nodeRight : right) {
                    Node root = new Node(i);
                    root.leftChild = node;
                    root.rightChild = nodeRight;
                    res.add(root);
                }
            }
        }
        return res;
    }

    /**
     * [LeetCode] 96. Unique Binary Search Trees 独一无二的二叉搜索树
     * Example:
     * <p>
     * Input: 3
     * Output: 5
     * Explanation:
     * Given n = 3, there are a total of 5 unique BST's:
     * <p>
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     *
     * @param n
     * @return
     */
    // 采用卡特兰数的方式进行处理
    public static int lc96UniqueBinarySearch(int n) {
        if (n < 2)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    /**
     * [LeetCode] 98. Validate Binary Search Tree 验证二叉搜索树
     * Example 1:
     * <p>
     * Input:
     * 2
     * / \
     * 1   3
     * Output: true
     * Example 2:
     * <p>
     * 5
     * / \
     * 1   4
     * / \
     * 3   6
     * Output: false
     * Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
     * is 5 but its right child's value is 4.
     *
     * @param root
     * @return
     */
    // 根据中序遍历
    public static boolean lc98isBinaryTree(Node root) {
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.leftChild;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                System.out.println(cur.data);
                cur = cur.rightChild;
            }
        }
        return true;
    }

    /**
     * [LeetCode] 100. Same Tree 判断相同树
     * Example 1:
     * <p>
     * Input:     1         1
     * / \       / \
     * 2   3     2   3
     * <p>
     * [1,2,3],   [1,2,3]
     * <p>
     * Output: true
     * Example 2:
     * <p>
     * Input:     1         1
     * /           \
     * 2             2
     * <p>
     * [1,2],     [1,null,2]
     * <p>
     * Output: false
     * Example 3:
     * <p>
     * Input:     1         1
     * / \       / \
     * 2   1     1   2
     * <p>
     * [1,2,1],   [1,1,2]
     * <p>
     * Output: false
     *
     * @param
     * @return
     */
    // 采用递归，或者中序遍历
    public static boolean lc100SameTree(Node p, Node q) {
        if (p == null && q == null)
            return true;
        if ((p == null && q != null) || (p != null && q == null))
            return false;
        return lc100SameTree(p.leftChild, q.leftChild) && lc100SameTree(p.rightChild, q.rightChild);
    }

    /**
     * [LeetCode] Symmetric Tree 判断对称树
     * For example, this binary tree is symmetric:
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * <p>
     * <p>
     * But the following is not:
     * <p>
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     *
     * @param root
     * @return
     */
    // 递归和非递归方式
    public static boolean lc101SymmetricTree(Node root) {
        return root == null || sameTree(root.leftChild, root.rightChild);
    }

    public static boolean sameTree(Node left, Node right) {
        if (left == null || right == null)
            return left == right;
        return (left.data == right.data) && sameTree(left.leftChild, right.rightChild) && sameTree(left.rightChild, right.leftChild);
    }

    /**
     * [LeetCode] 102. Binary Tree Level Order Traversal 二叉树层序遍历
     * For example:
     * Given binary tree {3,9,20,#,#,15,7},
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * <p>
     * return its level order traversal as:
     * <p>
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     *
     * @param root
     */
    // 使用队列
    public static void lc102LevelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur != null) {
                    if (cur != null) {
                        System.out.println(cur.data);
                        if (cur.leftChild != null) {
                            queue.offer(cur.leftChild);
                        }
                        if (cur.rightChild != null) {
                            queue.offer(cur.rightChild);
                        }
                    }
                }
            }
        }
    }

    /**
     * [LeetCode] 103. Binary Tree Zigzag Level Order Traversal 二叉树的之字形层序遍历
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * <p>
     * return its zigzag level order traversal as:
     * <p>
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     *
     * @param root
     * @return
     */
    // 使用一个flag
    public static List<List<Integer>> lc103zigLevelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = queue.remove();
                if (flag) {
                    list.add(cur.data);
                } else {
                    list.add(0, cur.data);
                }
                if (cur.leftChild != null) {
                    queue.add(cur.leftChild);
                }
                if (cur.rightChild != null) {
                    queue.add(cur.rightChild);
                }
            }
            flag = !flag;
        }
        return res;
    }

    /**
     * [LeetCode] 104. Maximum Depth of Binary Tree 二叉树的最大深度
     *
     * @param root
     * @return
     */
    // 按照层次遍历
    public static int lc104MaxDepth(Node root) {
        if (root == null)
            return 0;
        int depth = 1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.remove();
                if (cur.leftChild != null) {
                    queue.add(cur.leftChild);
                }
                if (cur.rightChild != null) {
                    queue.add(cur.rightChild);
                }
            }
            depth++;
        }
        return depth;
    }

    // 使用递归的方法
    public static int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.leftChild), maxDepth(root.rightChild));
    }

    /**
     * [LeetCode] 105. Construct Binary Tree from Preorder and Inorder Traversal 由先序和中序遍历建立二叉树
     * <p>
     * For example, given
     * <p>
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * Return the following binary tree:
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     *
     * @param
     * @return
     */
    public static Node lc105RebuildTree(int[] preOrder, int[] inOrder) {
        return reBuild(preOrder, inOrder, 0, 0, preOrder.length - 1);
    }

    public static Node reBuild(int[] preOrder, int[] inOrder, int cur, int left, int right) {
        if (left > right)
            return null;
        Node node = new Node(preOrder[cur]);
        int i = left;
        while (i <= right) {
            if (inOrder[i] == preOrder[i])
                break;
            i++;
        }
        node.leftChild = reBuild(preOrder, inOrder, cur + 1, left, i - 1);
        node.rightChild = reBuild(preOrder, inOrder, cur + 1 + i - left, i + 1, right);
        return node;
    }

    /**
     * [LeetCode] Convert Sorted Array to Binary Search Tree 将有序数组转为二叉搜索树
     *
     * @return
     */
    // 采用二分算法
    public static Node lc108(int[] num) {
        return binarySearchTree(num, 0, num.length - 1);
    }

    public static Node binarySearchTree(int[] num, int left, int right) {
        if (left > right) {
            return null;
        }
        int mind = left + (right - left) / 2;
        Node node = new Node(num[mind]);
        node.leftChild = binarySearchTree(num, left, mind - 1);
        node.rightChild = binarySearchTree(num, mind + 1, right);
        return node;
    }

    /**
     * [LeetCode] Convert Sorted List to Binary Search Tree 将有序链表转为二叉搜索树
     *
     * @param node
     * @return
     */
    // 采用快慢指针找到中点进行处理
    public static Node lc109LinkedTable(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return new Node(head.data);
        ListNode fast = head;
        ListNode slow = head;
        ListNode last = head;
        // 找到中点的位置
        while (fast.next != null && fast.next.next != null) {
            last = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        last.next = null;
        Node cur = new Node(slow.next.data);
        if (head != slow)
            cur.leftChild = lc109LinkedTable(head);
        cur.rightChild = lc109LinkedTable(fast);
        return cur;
    }

    /**
     * [LeetCode] Balanced Binary Tree 判断是否平衡二叉树
     *
     * @param root
     * @return
     */
    // 左右子树的高度差不超过1
    public static boolean lc110IsBalanceTree(Node root) {
        if (root == null)
            return true;
        if (Math.abs(depth(root.leftChild) - depth(root.rightChild)) > 1)
            return false;
        return lc110IsBalanceTree(root.leftChild) && lc110IsBalanceTree(root.rightChild);
    }

    // 获取深度
    public static int depth(Node root) {
        if (root == null)
            return 0;
        return Math.max(depth(root.leftChild), depth(root.rightChild)) + 1;
    }

    /**
     * [LeetCode] 111. Minimum Depth of Binary Tree 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public static int lc111MinDepth(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.remove();
                if (cur.leftChild == null && cur.rightChild == null)
                    return depth;
                if (cur.leftChild != null)
                    queue.add(cur.leftChild);
                if (cur.rightChild != null)
                    queue.add(cur.rightChild);
            }
            depth++;
        }
        return depth;
    }

    // 递归的方式
    public static int minDepth(Node root) {
        if (root == null)
            return 0;
        if (root.leftChild == null)
            return 1 + minDepth(root.rightChild);
        if (root.rightChild == null)
            return 1 + minDepth(root.leftChild);
        return 1 + Math.min(minDepth(root.leftChild), minDepth(root.rightChild));
    }

    /**
     * [LeetCode] 112. Path Sum 二叉树的路径和
     * Example:
     * <p>
     * Given the below binary tree and sum = 22,
     * <p>
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \      \
     * 7    2      1
     * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     *
     * @param root
     * @param target
     * @return
     */
    public static boolean lc112(Node root, int target) {
        if (root == null)
            return false;
        if (root.data == target && root.leftChild == null && root.rightChild == null)
            return true;
        return lc112(root.leftChild, target - root.data) || lc112(root.rightChild, target - root.data);
    }

    /**
     * [LeetCode] 113. Path Sum II 二叉树路径之和之二
     * For example:
     * Given the below binary tree and sum = 22,
     * <p>
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7    2  5   1
     * return
     * <p>
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]
     *
     * @param root
     * @return
     */
    // 采用回溯算法处理
    public static List<List<Integer>> lc113AllPath(Node root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        allPath(root, new ArrayList<Integer>(), res, target);
        return res;
    }

    public static void allPath(Node root, List<Integer> cur, List<List<Integer>> res, int target) {
        if (root == null)
            return;
        cur.add(root.data);
        if (root.data == target && root.leftChild == null && root.rightChild == null)
            res.add(new ArrayList<>(cur));
        else
        allPath(root.leftChild, cur, res, target - root.data);
        allPath(root.rightChild, cur, res, target - root.data);

        cur.remove(cur.size() - 1);
        return;
    }


}

class Node {
    public int data;
    public Node leftChild;
    public Node rightChild;

    public Node(int data) {
        this.data = data;
    }
}