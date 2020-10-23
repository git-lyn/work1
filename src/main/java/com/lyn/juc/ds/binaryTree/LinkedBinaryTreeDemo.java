package com.lyn.juc.ds.binaryTree;

import org.omg.CORBA.NO_IMPLEMENT;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-05-15 16:02
 *  二叉树的处理
 **/
public class LinkedBinaryTreeDemo {
    public static void main(String[] args) {
        LinkedTree linkedTree = new LinkedTree();
        int[] nums = {1,1,2,2,3,5,7,8,9,10,10,10};
        linkedTree.removeDuplicates(nums);
        linkedTree.insert(4);
        linkedTree.insert(5);
        linkedTree.insert(1);
        linkedTree.insert(3);
        linkedTree.insert(2);
        linkedTree.insert(6);
        linkedTree.insert(7);
        // 前序遍历
        System.out.println("前序遍历：");
        linkedTree.preOderByStack();
        linkedTree.preOrder3(linkedTree.root);
        System.out.println();
        linkedTree.preOrderTraverse();
        System.out.println();
        // 中序遍历
        System.out.println("中序遍历：");
        linkedTree.inOrder3(linkedTree.root);
        System.out.println();
//        linkedTree.inOrderTraverse();
        linkedTree.inOrderByStacks2();
        System.out.println();
        // 后序遍历
        System.out.println("后序遍历：");
        linkedTree.postOrderByStack();
        linkedTree.postOrder3(linkedTree.root);
        System.out.println();
        linkedTree.postOrderTraverse();
        // 二叉树的高度
        System.out.println("二叉树的高度：" + linkedTree.treeHigh() + " : " + linkedTree.getHigh(linkedTree.root));
        // 二叉树节点的个数
        System.out.println("节点的个数：" + linkedTree.nodeNumber() + ": " + linkedTree.getSize(linkedTree.root));
        // 层次遍历二叉树
        System.out.println("按照层次遍历二叉树：");
        linkedTree.levelByQueue();
        System.out.println("非递归中序遍历二叉树:");
        // 非递归中序遍历二叉树
        linkedTree.inOrderByStack4();
        linkedTree.changeTwo();
    }
}

class Node{
    public int data;
    public Node leftChild;
    public Node rightChild;

    public Node(int data) {
        this.data = data;
    }
}

class LinkedTree{
    public Node root;

    // 插入节点信息
    public void insert(int data){
        Node newNode = new Node(data);
        if(root == null){
            root = newNode;
            return;
        }
        Node parent = root;
        Node current = root;
        boolean isLeftChild = true;
        while (current != null){
            parent = current;
            if(current.data > data){
               isLeftChild = true;
               current = current.leftChild;
            }else{
                isLeftChild = false;
                current = current.rightChild;
            }
        }
        if (isLeftChild) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
    }

    /**
     *  使用非递归的方式，实现二叉树的中序非递归遍历
     *   使用栈来实现，
     */
    public void inOrderByStack1(){
        Deque<Node> stack = new LinkedList<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }
            if(!stack.isEmpty()){
                current = stack.pop();
                System.out.print(current.data + " ");
                current = current.rightChild;
            }
        }
    }


    /**
     * 使用非递归的方式实现二叉树的非递归遍历
     */
    public void inOrderByStack2(){
        Deque<Node> stack = new LinkedList<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.data + " ");
                current = current.rightChild;
            }
        }
    }

    /**
     *  按照层次遍历二叉树
     *   使用队列的方式来进行存储数据
     */
    public void levelOrderByQueue3(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.data + " ");
            if (temp.leftChild != null) {
                queue.add(temp.leftChild);
            }
            if (temp.rightChild != null) {
                queue.add(temp.rightChild);
            }
        }
    }


    /**
     * 按照层次遍历二叉树
     *  使用队列的
     */
    public void levelOrderByQueue4(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.data + " ");
            if(temp.leftChild != null) queue.add(temp.leftChild);
            if(temp.rightChild != null) queue.add(temp.rightChild);
        }
    }

    /**
     * 采用非递归的方式进行中序遍历二叉树
     */
    public void inOrderByStatck4(){
        Deque<Node> stack = new LinkedList<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.data + " ");
                current = current.rightChild;
            }
        }
    }


    /**
     * 前序遍历二叉树
     *  root -  left  -   right
     */
    public void preOrderTraverse(){
        preOrderTraverse(root);
    }
    public void preOrderTraverse(Node root){
        if(root != null){
            // root
            System.out.print(root.data + " ");;
            // left
            preOrderTraverse(root.leftChild);
            // right
            preOrderTraverse(root.rightChild);
        }
    }

    /**
     * 中序遍历二叉树
     *  left -  root -  right
     */
    public void inOrderTraverse(){
        inOrderTraverse(root);
    }

    public void inOrderTraverse(Node root){
        if(root != null){
            // left
            inOrderTraverse(root.leftChild);
            // root
            System.out.print(root.data + " ");
            // right
            inOrderTraverse(root.rightChild);
        }
    }

    /**
     * 后序遍历二叉树
     * left - right - root
     */
    public void postOrderTraverse(){
        postOrderTraverse(root);
    }
    public void postOrderTraverse(Node root){
        if(root != null){
            // left
            postOrderTraverse(root.leftChild);
            // right
            postOrderTraverse(root.rightChild);
            // root
            System.out.print(root.data + " ");
        }
    }

    /**
     * 求解二叉树的高度
     */
    public int treeHigh(){
        return treeHigh(root);
    }

    public int treeHigh(Node root){
        if(root != null){
            int leftChild = treeHigh(root.leftChild);
            int rightChild = treeHigh(root.rightChild);
            return leftChild > rightChild ? leftChild + 1 : rightChild + 1;
        }
        return 0;
    }

    /**
     * 二叉树的节点个数
     */
    public int nodeNumber(){
        return nodeNumber(root);
    }
    public int nodeNumber(Node root){
        if(root != null){
            int left = nodeNumber(root.leftChild);
            int right = nodeNumber(root.rightChild);
            return  left + right + 1;
        }
        return 0;
    }

    /**
     * 层次遍历二叉树
     * 使用队列存放节点数据，
     * 想将root节点出队列，同时将节点的左右子节点按照顺序添加到队列中
     */
    public void levelOrderByQueue(){
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() != 0){
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node temp = queue.poll();
                System.out.print(temp.data + " ");
                if (temp.leftChild != null) {
                    queue.add(temp.leftChild);
                }
                if (temp.rightChild != null) {
                    queue.add(temp.rightChild);
                }
            }
        }
        System.out.println();
    }

    /**
     *  使用层次遍历二叉树，借助于队列结构来完成。
     *   先将根节点放进队列，然后出队，并将左右子节点
     *   按照顺序添加到队列中去。
     */
    public void levelOrderByQueue2(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node temp = queue.poll();
                System.out.print(temp.data + " ");
                if (temp.leftChild != null) {
                    queue.add(temp.leftChild);
                }
                if (temp.rightChild != null) {
                    queue.add(temp.rightChild);
                }
            }
        }
        System.out.println();
    }



    public void levelOrderByQueue33(){
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        while (queue.size() != 0) {
            int len = queue.size();
            for(int i = 0;i < len;i++) {
                Node temp = queue.poll();
                System.out.println(temp.data);
                if (root.leftChild != null) {
                    queue.add(root.leftChild);
                }
                if (root.rightChild != null) {
                    queue.add(root.rightChild);
                }
            }
        }
    }


    /**
     * 使用非递归的方式，来实现相应的中序遍历
     * 借助于栈来实现
     */
    public void inOrderByStack(){
        Deque<Node> stack = new LinkedList<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.data + " ");
                current = current.rightChild;
            }
        }
    }


    public void inOrderByStack22(){
        Deque<Node> stack = new LinkedList<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.println(current.data);
                current = current.rightChild;
            }
        }

    }


    /**
     * 前序遍历二叉树： root - left - right
     */
    public void preOrder3(Node root){
        if (root != null) {
            // root
            System.out.print(root.data + " ");
            // left
            preOrder3(root.leftChild);
            // right
            preOrder3(root.rightChild);
        }
    }

    /**
     * 中序遍历二叉树： left - root - right
     */
    public void inOrder3(Node root){
        if (root != null) {
            // left
            inOrder3(root.leftChild);
            // root
            System.out.print(root.data + " ");
            // right
            inOrder3(root.rightChild);
        }
    }

    /**
     * 后序遍历二叉树： left - right - root
     * @param root
     */
    public void postOrder3(Node root) {
        if (root != null) {
            // left
            postOrder3(root.leftChild);
            // right
            postOrder3(root.rightChild);
            // root
            System.out.print(root.data + " ");
        }
    }


    /**
     * 得到二叉树的高度
     */
    public int getHigh(Node root) {
        if (root != null) {
            int left = getHigh(root.leftChild);
            int right = getHigh(root.rightChild);
            return left > right ? left + 1 : right + 1;
        }
        return 0;
    }

    /**
     * 得到二叉树的节点的个数
     */
    public int getSize(Node root) {
        if (root != null) {
            int left = getSize(root.leftChild);
            int right = getSize(root.rightChild);
            return left + right + 1;
        }
        return  0;
    }

    /**
     * 使用非递归的方式实现二叉树的中序遍历
     */
    public void inOrderByStack5(){
        Deque<Node> stack = new LinkedList<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.data + " ");
                current = current.rightChild;
            }
        }
    }

    /**
     * 使用队列实现二叉树的层次遍历
     */
    public void inOrderByQueue(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.data + " ");
            if(temp.leftChild != null) queue.add(temp.leftChild);
            if(temp.rightChild != null) queue.add(temp.rightChild);
        }
    }

    /**
     * 使用栈来实现十进制到二进制的转换
     */
    public void changeTwo(){
        int number = 17;
        int mod = 0;
        int result = number;
        String str = "";
        Deque<Integer> stack = new LinkedList<>();
        while (result > 0){
            // 取余数
            mod = result % 2;
            result = result / 2;
            str = mod + str;
            stack.push(mod);
        }
        System.out.println(str);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }


    /**
     * 按照层次进行遍历二叉树
     *  通过队列的方式进行处理
     */
    public void levelByQueue(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.data + " ");
            if(temp.leftChild != null) queue.add(temp.leftChild);
            if(temp.rightChild != null) queue.add(temp.rightChild);
        }
    }

    /**
     * 使用非递归的方式实现二叉树的中序遍历
     */
    public void inOrderByStack4(){
        Deque<Node> stack = new LinkedList<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                current = current.rightChild;
            }
        }
    }

    /**
     *   按照层级进行遍历二叉树
     */
    public void levelByQueue6(){
        //  创建对应的队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.data + " ");
            if(temp.leftChild != null) queue.add(temp.leftChild);
            if(temp.rightChild != null) queue.add(temp.rightChild);
        }
    }

    /**
     * 采用非递归的方式实现相应的
     * 二叉树的中序遍历
     */
    public void inOrderByStack6(){
        // 创建对应的栈
        Deque<Node> stack = new LinkedList<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                current = current.rightChild;
            }
        }
    }

    /**
     * 按照层次进行遍历二叉树
     *  使用队列进行处理
     */
    public void levelByQueue7(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() != 0){
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node temp = queue.poll();
                System.out.print(temp.data + " ");
                if(temp.leftChild != null) queue.add(temp.leftChild);
                if(temp.rightChild != null) queue.add(temp.rightChild);
            }
        }
        System.out.println();
    }

//    public void levelOrderByQueue(){
//        if (root == null) {
//            return;
//        }
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(root);
//        while (queue.size() != 0){
//            int len = queue.size();
//            for (int i = 0; i < len; i++) {
//                Node temp = queue.poll();
//                System.out.print(temp.data + " ");
//                if (temp.leftChild != null) {
//                    queue.add(temp.leftChild);
//                }
//                if (temp.rightChild != null) {
//                    queue.add(temp.rightChild);
//                }
//            }
//        }
//        System.out.println();
//    }

    /**
     * 采用非递归的方式来实现二叉树的前序遍历
     */
    public void preOderByStack(){
        Deque<Node> stack = new LinkedList<>();
        Node current = root;
//        while (current != null || !stack.isEmpty()) {
//            while (current != null) {
//                stack.push(current);
//                current = current.leftChild;
//            }
//            if (!stack.isEmpty()) {
//                current = stack.pop();
//                System.out.print(current.data + " ");
//                current = current.rightChild;
//            }
//        }
        while (!stack.isEmpty()) {
            Node  temp = stack.pop();
            System.out.print(temp.data + " ");
            if(temp.rightChild != null) stack.push(temp.rightChild);
            if(temp.leftChild != null) stack.push(temp.leftChild);
        }
        System.out.println();
    }

    public void preOderByStack22() {
        Deque<Node> stack = new LinkedList<>();
        Node current = root;
        stack.push(root);
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            System.out.println(temp.data);
            if(current.rightChild != null) stack.push(temp.rightChild);
            if(current.leftChild != null) stack.push(temp.leftChild);
        }
    }

    /**
     * 采用非递归的方式进行二叉树的后序遍历
     */
    public void postOrderByStack(){
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node temp = stack.peek();
            if (temp.leftChild == null && temp.rightChild == null) {
                System.out.print(temp.data + " ");
                stack.pop();
            }else{
                if (temp.rightChild != null) {
                    stack.push(temp.rightChild);
                    temp.rightChild = null;
                }
                if (temp.leftChild != null) {
                    stack.push(temp.leftChild);
                    temp.leftChild = null;
                }
            }
        }
        System.out.println();
    }


    /**
     * 判断两颗树是否相同
     * @param
     * @return
     */
    public boolean isSame(Node p,Node q){
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        return p.data == q.data && isSame(p.leftChild, q.leftChild) && isSame(p.rightChild, q.rightChild);
    }

    /**
     * 删除数组中的重复的元素
     * 先对数组进行排序，用两个pointerers，一个用于新数组的生成，一个用于遍历原来的数组；
     */
    public int removeDuplicates(){
        int[] nums = {1,1,2,2,3,5,7,8,9,10,10,10};
        if(nums.length == 0) return 0;
        int i = 1;
        for(int j = 1;j < nums.length;j++){
            if (nums[j] != nums[i - 1]) {
                nums[i++] = nums[j];
            }
        }
        for(int a : nums){
            System.out.print(a + " ");
        }
        System.out.println();
        return i;
    }


    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i - 1])
                nums[i++] = nums[j];
        }
        for(int a : nums){
            System.out.print(a + " ");
        }
        System.out.println();
        return i;
    }

    /**
     * 二叉树的遍历方式
     */


    // 前序遍历： root  -  left -  right
    public void preOrderTree(Node node) {
        if (node != null) {
            // root
            System.out.print(node.data + " ");
            // left
            preOrderTree(node.leftChild);
            // right
            preOrderTree(node.rightChild);
        }
    }

    // 中序遍历二叉树: left  -  root  -  right
    public void inOrderTree(Node node) {
        if (node != null) {
            // left
            inOrderTree(node.leftChild);
            // root
            System.out.print(node.data + " ");
            // right
            inOrderTree(node.rightChild);
        }
    }

    // 后序遍历二叉树： left  -  right -  root
    public void postOrderTree(Node node) {
        if (node == null) {
            // left
            postOrderTree(node.leftChild);
            // right
            postOrderTree(node.rightChild);
            System.out.print(node.data + " ");
        }
    }

    /**
     * 采用非递归的方式，实现二叉树的中序遍历
     *  使用栈进行中序遍历
     */
    public void inOrderByStacks2(){
        Deque<Node> stack = new LinkedList<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.data + " ");
                current = current.rightChild;
            }
        }
    }

    /**
     * 通过队列的方式，进行层次遍历二叉树
     */
    public void listByQueue(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.data + " ");
            if(root.leftChild != null) queue.add(root.leftChild);
            if(root.rightChild != null) queue.add(root.rightChild);
        }
    }

    /**
     * 获取二叉树的高度
     */
    public int getTreeHigh(Node node){
        if (node != null) {
            int left = getTreeHigh(node.leftChild);
            int right = getTreeHigh(node.rightChild);
            return left > right ? left + 1 : right + 1;
        }
        return 0;
    }

    /**
     * 获取二叉树的节点的个数
     */
    public int getTreeNumber(Node node) {
        if (node != null) {
            int left = getTreeNumber(node.leftChild);
            int right = getTreeNumber(node.rightChild);
            return left + right + 1;
        }
        return 0;
    }

    /**
     * 中序遍历二叉树：采用非递归的方式，使用栈的 方式进行处理
     */
    public void inOrderBystacks3(){
        Deque<Node> stack = new LinkedList<>();
//        stack.add(root);
        Node curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.leftChild;
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                System.out.print(curr.data + " ");
                curr = curr.rightChild;
            }
        }
    }

    /**
     * 按照层次进行遍历二叉树：使用队列的方式
     */
    public void listByQueue2(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.data + " ");
            if(root.leftChild != null) queue.add(root.leftChild);
            if(root.rightChild != null) queue.add(root.rightChild);
        }
    }

}