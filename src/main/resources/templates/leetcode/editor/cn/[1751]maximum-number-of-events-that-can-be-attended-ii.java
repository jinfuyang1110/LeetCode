package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给你一个 events 数组，其中 events[i] = [startDayi, endDayi, valuei] ，表示第 i 个会议在 
//startDayi 天开始，第 endDayi 天结束，如果你参加这个会议，你能得到价值 valuei 。同时给你一个整数 k 表示你能参加的最多会议数目。 
//
// 你同一时间只能参加一个会议。如果你选择参加某个会议，那么你必须 完整 地参加完这个会议。会议结束日期是包含在会议内的，也就是说你不能同时参加一个开始日期与
//另一个结束日期相同的两个会议。
//
// 请你返回能得到的会议价值 最大和 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
//输出：7
//解释：选择绿色的活动会议 0 和 1，得到总价值和为 4 + 3 = 7 。 
//
// 示例 2： 
//
// 
//
// 
//输入：events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
//输出：10
//解释：参加会议 2 ，得到价值和为 10 。
//你没法再参加别的会议了，因为跟会议 2 有重叠。你 不 需要参加满 k 个会议。 
//
// 示例 3： 
//
// 
//
// 
//输入：events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
//输出：9
//解释：尽管会议互不重叠，你只能参加 3 个会议，所以选择价值最大的 3 个会议。 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= events.length 
// 1 <= k * events.length <= 10⁶ 
// 1 <= startDayi <= endDayi <= 10⁹ 
// 1 <= valuei <= 10⁶ 
// 
// Related Topics 数组 二分查找 动态规划 👍 54 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events,(a,b)->a[1]-b[1]);
        int n=events.length;
        //dp[i][j] 前i个会，参数k个的最大值
        int[][] dp=new int[n+1][k+1];
        for (int i = 1; i <= n; i++) {
            int l=1,r=i-1,t=events[i-1][0];
            while (l<=r){
                int m=l+(r-l>>1), cur=events[m-1][1];
                if (cur>=t)r=m-1;
                else l=m+1;
            }
            int index= r>0?r:0;
            for (int j = 1; j <= k; j++) {
                //状态转移，max转移的，所以找最大不冲突那个max;
                dp[i][j]=Math.max(dp[i-1][j],dp[index][j-1]+events[i-1][2]);
            }
        }
        return dp[n][k];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
