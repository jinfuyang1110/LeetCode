package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。 
//
// 注意 这个数列必须是 严格 递增的。 
//
// 
//
// 示例 1: 
//
// 
//输入: [1,3,5,4,7]
//输出: 2
//解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
// 
//
// 示例 2: 
//
// 
//输入: [2,2,2,2,2]
//输出: 5
//解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
// 
//
// 
//
// 提示: 
//
// 
//
// 
// 1 <= nums.length <= 2000 
// -10⁶ <= nums[i] <= 10⁶ 
// 
// Related Topics 树状数组 线段树 数组 动态规划 👍 614 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int n;
    int[][] t;
    int lowbit(int x) {
        return x & -x;
    }
    int[] query(int x) {
        int len = 0, cnt = 0;
        for (int i = x; i > 0; i-=lowbit(i)) {
            if (len==t[i][0])cnt+=t[i][1];
            else if (len<t[i][0]){
                len=t[i][0];
                cnt=t[i][1];
            }
        }
        return new int[]{len, cnt};
    }
    void add(int x, int[] info) {
        for (int i = x; i <=n; i+=lowbit(i)) {
            int len=t[i][0],cnt=t[i][1];
            if (len==info[0]) cnt+=info[1];
            else if (len<info[0]){
                len=info[0];
                cnt=info[1];
            }
            t[i][0]=len;t[i][1]=cnt;
        }
    }
    public int findNumberOfLIS(int[] nums) {
        n = nums.length;
        t=new int[n+1][2];
        // 离散化
        int[] clone = nums.clone();
        Map<Integer,Integer> map =new HashMap<>();
        Arrays.sort(clone);
        for (int i = 0,index=1; i <n; i++) {
            if (!map.containsKey(clone[i])) map.put(clone[i],index++);
        }
        for (int i = 0; i < n; i++) {
            int x=map.get(nums[i]);
            int[]info=query(x-1);
            add(x,new int[]{info[0]+1,Math.max(info[1],1)});
        }
        return query(n)[1];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
