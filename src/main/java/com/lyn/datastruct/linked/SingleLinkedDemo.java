package com.lyn.datastruct.linked;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-10-09 20:59
 *
 * 单向链表的处理:
 *
 **/
public class SingleLinkedDemo {
    public static void main(String[] args) {
        LinkedTable linkedTable = new LinkedTable();
        linkedTable.insertLinked(1);
        linkedTable.insertLinked(2);
        linkedTable.insertLinked(3);
        linkedTable.insertLinked(4);
        linkedTable.insertLinked(5);
        linkedTable.getAll();
    }
}

// 创建节点类：
// 包含数据和下一个节点
class Node{
    public int data; // 节点的数据部分
    public Node next; // 下一个节点的引用
    // 创建节点对应的构造器
    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    // 获取对应的数据信息
    public int getData(){
        return data;
    }


}

// 创建对应的链表类
class LinkedTable{
    // 创建对应的头节点
    public Node head = null;

    // 链表中添加数据
    public void insert(int data){
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
            return;
        }
        // 头节点不为空
        head.next = newNode;
        // 找到头节点的next的为null尾部节点
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        // 当前新添加的节点的next为空
        newNode.next = current.next;
        current.next = newNode;

    }

    // 插入头节点

    // 按照从大到小的顺序插入节点信息
    public void insertLinked(int data){
        // 创建要插入的节点的信息
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
            return;
        }
        // 头节点不为空，按照顺序添加节点
        Node current = head.next;
        Node parent = head;
        while (current != null) {
            if (current.data < data) {
                current = current.next;
                parent = parent.next;
            }else{
                break;
            }
        }
        newNode.next = parent.next;
        parent.next = newNode;
    }

    // 遍历节点所有的信息
    public void getAll(){
        if (head == null) {
            System.out.println("节点信息为空");
            return;
        }
        Node current = head;
        System.out.print("节点信息为: ");
        while (current != null) {
            System.out.print(current.data + " ");
        }
        System.out.println();
    }


}
