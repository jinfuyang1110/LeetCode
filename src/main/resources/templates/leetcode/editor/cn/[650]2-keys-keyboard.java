package com.example.hellodocker.leetCode.leetcode.editor.cn;
//最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作： 
//
// 
// Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。 
// Paste（粘贴）：粘贴 上一次 复制的字符。 
// 
//
// 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。 
//
// 
//
// 示例 1： 
//
// 
//输入：3
//输出：3
//解释：
//最初, 只有一个字符 'A'。
//第 1 步, 使用 Copy All 操作。
//第 2 步, 使用 Paste 操作来获得 'AA'。
//第 3 步, 使用 Paste 操作来获得 'AAA'。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1000 
// 
// Related Topics 数学 动态规划 👍 466 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minSteps(int n) {
        //dp[i][j] i记事本上,j粘贴
        int INF=10001;
        int[][]dp=new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i],INF);
        }
        dp[1][0]=0;dp[1][1]=1;
        for (int i = 2; i <= n; i++) {
            int min=INF;
            for (int j = 1; j < i; j++) {
                min=Math.min(dp[i-j][j]+1,min);
                dp[i][j]=dp[i-j][j]+1;
            }
            dp[i][i]=min+1;
        }
        int ans=INF;
        for (int i = 0; i <= n; i++) {
            ans=Math.min(ans,dp[n][i]);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
