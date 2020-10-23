package com.lyn.juc.leet.leetcode;

/**
 * @program: projects
 * @author: lyn
 * 动态规划： 背包问题
 * * @create: 2020-01-29 17:37
 **/
public class DPPacketProblem {
    public static void main(String[] args) {
        packet();
    }

    // 0-1背包数据的处理
    public static void packet() {
        // 物品的重量
        int[] w = {1, 4, 3};
        // 物品的价值
        int[] val = {1500, 3000, 2000};
        // 背包的容量
        int m = 4;
        // 物品的数量
        int n = w.length;
        // 创建二维数组
        int[][] dp = new int[n + 1][m + 1];
        // 得到相应的数据组成
        int[][] path = new int[dp.length][dp[0].length];
        for (int i = 0; i < dp.length; i++) {
            // 第一列为0
            dp[i][0] = 0;
        }
        for (int i = 0; i < dp[0].length; i++) {
            // 第一行为0
            dp[0][i] = 0;
        }
        // 下标从1开始
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                // 判断第 i 个物品释放放进到背包里
                if (w[i - 1] > j) {
                    // 第 i个物品的重量大于背包的容量,物品就不放进背包
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 第 i个物品放进到背包
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + val[i - 1]);
                    if (dp[i - 1][j] < dp[i - 1][j - w[i - 1]] + val[i - 1]) {
                        path[i][j] = 1;
                        dp[i][j] = dp[i - 1][j - w[i - 1]] + val[i - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        // 将对应的结果输出
        for(int i = 0;i < dp.length;i++) {
            for(int j = 0;j < dp[i].length;j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }


        // 得到最优结果的数据组成
        System.out.println("############");
        // 行  i
        int h = path.length - 1;
        // 列  j
        int l = path[0].length - 1;
        // 从path的最后开始查找
        while (h > 0 && l > 0) {
            if (path[h][l] == 1) {
                System.out.println("对应的物品的下标为: " + h);
                // 将对应的 i 号物品的重量从  j 背包中移除
                l -= l - w[h - 1];
            }
            h--;
        }


    }
}
