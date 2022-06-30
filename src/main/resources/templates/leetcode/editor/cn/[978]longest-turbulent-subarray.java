package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给定一个整数数组 arr ，返回 arr 的 最大湍流子数组的长度 。 
//
// 如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是 湍流子数组 。 
//
// 更正式地来说，当 arr 的子数组 A[i], A[i+1], ..., A[j] 满足仅满足下列条件时，我们称其为湍流子数组： 
//
// 
// 若 i <= k < j ：
//
// 
// 当 k 为奇数时， A[k] > A[k+1]，且 
// 当 k 为偶数时，A[k] < A[k+1]； 
// 
// 
// 或 若 i <= k < j ：
// 
// 当 k 为偶数时，A[k] > A[k+1] ，且 
// 当 k 为奇数时， A[k] < A[k+1]。 
// 
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [9,4,2,10,7,8,8,1,9]
//输出：5
//解释：arr[1] > arr[2] < arr[3] > arr[4] < arr[5] 
//
// 示例 2： 
//
// 
//输入：arr = [4,8,12,16]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：arr = [100]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 4 * 10⁴ 
// 0 <= arr[i] <= 10⁹ 
// 
// Related Topics 数组 动态规划 滑动窗口 👍 205 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length, max = 1;
        int[][] dp = new int[n][2];//dp[i][j]以arr[i]结尾的最长湍流子数,j代表情况
        dp[0][1] = 1;
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                dp[i][(i - 1) & 1] = dp[i - 1][(i - 1) & 1] + 1;
                dp[i][i & 1] = 1;
            } else if (arr[i] < arr[i - 1]) {
                dp[i][(i - 1) & 1] = 1;
                dp[i][i & 1] = dp[i - 1][i & 1] + 1;
            } else {
                dp[i][0] = 1;
                dp[i][1] = 1;
            }
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
