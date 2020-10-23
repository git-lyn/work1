package com.lyn.datastruct.linked;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-11-02 14:27
 * 单向链表的处理
 **/
public class MyLinkedDemo {
    public static void main(String[] args) {
        MyLinkedList a = new MyLinkedList();
//        a.reverse(null);
        MyLinkedList.reverse2();
    }
}

// 链表节点的
class MyNode {
    // 数据
    public int data;
    // 下一个节点
    public MyNode next;

    public MyNode(int data, MyNode node) {
        this.data = data;
        this.next = node;
    }
}

// 创建对应的链表
class MyLinkedList {
    public MyNode head = new MyNode(-99, null);


    /**
     * 1. 单向链表的反转
     */

    public static MyNode reverse(MyNode linked) {
        linked = new MyNode(1, null);
        linked.next = new MyNode(2, null);
        linked.next.next = new MyNode(3, null);
        linked.next.next.next = new MyNode(4, null);
        if (linked == null) {
            return null;
        }
        // 普通的方式反转单向链表
        MyNode curr = linked;
        MyNode rev = null;
        while (curr != null) {
            MyNode temp = curr;
            temp.next = rev;
            rev = temp;
            curr = curr.next;
        }

        while (rev != null) {
            System.out.println(rev.data);
            rev = rev.next;
        }

        return curr;
    }


    public static MyNode reverse() {
        MyNode linked = new MyNode(1, null);
        linked.next = new MyNode(2, null);
        linked.next.next = new MyNode(3, null);
        linked.next.next.next = new MyNode(4, null);

        MyNode pCur = linked;
        MyNode pRev = null;
        while (pRev != null) {
            MyNode pTemp = pCur;
            pCur = pCur.next;
            pTemp.next = pRev;
            pRev = pTemp;
        }
//        while (pRev != null) {
//            System.out.println(pRev.data);
//            pRev = pRev.next;
//        }
        return pRev;
    }


    /**
     * 正确的单链表的翻转
     * @return
     */
    public static MyNode reverse2() {
        MyNode linked = new MyNode(1, null);
        linked.next = new MyNode(2, null);
        linked.next.next = new MyNode(3, null);
        linked.next.next.next = new MyNode(4, null);

        MyNode pre = linked;
        MyNode cur = linked.next;
        while (cur != null) {
            MyNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

//        while (cur != null) {
//            System.out.println(cur.data);
//            cur = cur.next;
//        }
//        linked.next = null;

        return pre;
    }


    /**
     * 判断单向链表是否有环
     * 采用快慢指针的方式进行判断: 快指针走两步，慢指针走一步
     */
    public static boolean isCircle(MyNode linked) {
        MyNode quick = linked;
        MyNode slow = linked;
        while (linked != null) {
            if (quick.next.next != null) {
                quick = quick.next.next;
                slow = slow.next;
            }
            if (quick.data == slow.data) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    /**
     * 3. 两个有序链表的合并
     */
    public static MyNode mergeTwoLinked(MyNode a, MyNode b) {
        MyNode result = new MyNode(-99, null);
        while (a != null && b != null) {
            if (a.data < b.data) {
                result.next = a;
                result.next.next = null;
                a = a.next;
            } else {
                result.next = b;
                result.next.next = null;
                b = b.next;
            }
            result = result.next;
        }
        if (a != null) {
            result.next = a;
        }
        if (b != null) {
            result.next = b;
        }
        return result.next;
    }

    /**
     * 4. 删除链表的倒数第n个节点
     */
    public static MyNode daoshu(MyNode linked, int num) {
        MyNode temp = linked;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        int step = length - num;
        while (step > 0) {
            linked = linked.next;
            step--;
        }
        MyNode res = linked.next;
        linked.next = linked.next.next;
        return res;
    }

    /**
     * 求解链表的中间节点
     */
    public static MyNode middleNode(MyNode linked) {

        return null;
    }


}



