package com.lyn.juc.dp;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-08-27 21:20
 **/
public class PackDemo {
    public static void main(String[] args) {
        // 背包容量
        int m = 10;
        // 物体编号
        int n = 3;
        // 物体体积
//        int[] w = {2, 3, 4};
        int[] w = {3, 4,5};
        // 物体价值
//        int[] v = {3,4,5};
        int[] v = {4,5,6};
        int[][] solution = back_solution(m, n, w, v);
        for(int i = 0;i < solution.length;i++) {
            for(int j = 0;j < solution[i].length;j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param m 背包容量
     * @param n 物品编号
     * @param w 物品体积
     * @param v 物品价格
     * @return
     */
    public static int[][] back_solution(int m,int n,int[] w,int[] v){
        int[][] c = new int[n + 1][m + 1];
        for(int i = 0;i < n + 1;i++){
            c[i][0] = 0;
        }
        for(int j = 0;j < m + 1;j++) {
            c[0][j] = 0;
        }

        // 从1开始进
        for(int i = 1;i < n + 1;i++) {// i表示物品编号
            for(int j = 1;j < m + 1;j++) { // j表示物品的体积
                // 判断第i个物品的体积是否大于背包的容量
                if(w[i - 1] <= j){
                    // 判断物品是否装入到背包中
                    if (c[i - 1][j] < c[i - 1][j - w[i - 1]] + v[i - 1]) {
                        // 如果放进到背包中，背包的价值为:c[i-1][j-w[i-1]] + v[i-1]
                        c[i][j] = c[i - 1][j - w[i - 1]] + v[i - 1];
                    }else{
                        // 如果不放进背包中,c[i][j] = c[i-1][j]
                        c[i][j] = c[i - 1][j];
                    }

                }else{
                    c[i][j] = c[i - 1][j];
                }
            }
        }



        return c;
    }
}
