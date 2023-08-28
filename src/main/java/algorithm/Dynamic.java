package algorithm;


import java.util.Arrays;

class Knapsack {
    // 动态规划函数，返回最大价值
    public static int knapsack(int capacity, int[] weights, int[] values, int n) {
        int[][] dp = new int[n + 1][capacity + 1]; // 创建动态规划表格

        // 填充动态规划表格
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] <= j) { // 当前物品的重量小于等于背包容量
                    // 选择放入当前物品或不放入当前物品，取较大值
                    dp[i][j] = Math.max(values[i - 1] + dp[i - 1][j - weights[i - 1]], dp[i - 1][j]);
                } else { // 当前物品的重量大于背包容量，不放入当前物品
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][capacity]; // 返回最大价值
    }

    public static void main(String[] args) {
        int capacity = 10; // 背包容量
        int[] weights = {2, 3, 4, 5}; // 物品重量
        int[] values = {3, 4, 5, 6}; // 物品价值
        int n = weights.length; // 物品数量

        int max_value = knapsack(capacity, weights, values, n);
        System.out.println("最大价值为：" + max_value);
    }
}


