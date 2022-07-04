package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。 
//
// 示例 2： 
//
// 
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 
//。 
//
// 
//
// 提示： 
//
// 
// 0 <= k <= 100 
// 0 <= prices.length <= 1000 
// 0 <= prices[i] <= 1000 
// 
// Related Topics 数组 动态规划 👍 754 👎 0


import java.lang.reflect.Array;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n==0) return 0;
        int[][][] dp = new int[n][2][k + 1];
        Arrays.fill(dp[0][1], -prices[0]);
        for (int i = 1; i < n; i++) {
            for (int p = 1; p <= k; p++) {
                dp[i][0][p]=Math.max(dp[i-1][0][p],dp[i-1][1][p-1]+prices[i]);
            }
            for (int p = 0; p <k; p++) {
                dp[i][1][p]=Math.max(dp[i-1][1][p],dp[i-1][0][p]-prices[i]);
            }
        }
        return dp[n - 1][0][k];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
