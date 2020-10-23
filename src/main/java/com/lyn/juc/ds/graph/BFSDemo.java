package com.lyn.juc.ds.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-11-26 21:11
 **/
public class BFSDemo {



}

class Graph{
   private LinkedList<Integer>[] datas;
   private int v; // 顶点的个数


    public void bfs(int s,int t){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        boolean[] visited = new boolean[v];
        visited[s] = true;
        // 遍历路径
        int[] prev = new int[v];
        for(int i = 0;i < v;i++){
            prev[i] = -1;
        }
        while (queue.size() != 0){
            // 获取队列的头结点
            int p = queue.poll();
            for(int i = 0;i < datas[p].size();i++){
                int q = datas[p].get(i);
                if(!visited[q]){
                    prev[q] = p;
                    if(q == t){
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
    }
    }

}
