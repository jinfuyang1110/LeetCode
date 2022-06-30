package com.example.hellodocker.leetCode.leetcode.editor.cn;
//n 名士兵站成一排。每个士兵都有一个 独一无二 的评分 rating 。 
//
// 每 3 个士兵可以组成一个作战单位，分组规则如下： 
//
// 
// 从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k] 
// 作战单位需满足： rating[i] < rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[
//k] ，其中 0 <= i < j < k < n 
// 
//
// 请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：rating = [2,5,3,4,1]
//输出：3
//解释：我们可以组建三个作战单位 (2,3,4)、(5,4,1)、(5,3,1) 。
// 
//
// 示例 2： 
//
// 
//输入：rating = [2,1,3]
//输出：0
//解释：根据题目条件，我们无法组建作战单位。
// 
//
// 示例 3： 
//
// 
//输入：rating = [1,2,3,4]
//[1,2,3],[1,2,4],[1,3,4],[2,3,4]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// n == rating.length 
// 3 <= n <= 1000 
// 1 <= rating[i] <= 10^5 
// rating 中的元素都是唯一的 
// 
// Related Topics 树状数组 数组 动态规划 👍 106 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int n;
    int[] t;

    int lowbit(int x) {
        return x & -x;
    }

    public void add(int index, int val) {
        for (int i = index; i <= n; i += lowbit(i)) {
            t[i] += val;
        }
    }

    int query(int index) {
        int res = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            res += t[i];
        }
        return res;
    }

    public int numTeams(int[] rating) {
        n = rating.length;
        t = new int[n + 1];
        int cnt=0;
        int[] iless = new int[n];
        int[] imore = new int[n];
        int[] kless = new int[n];
        int[] kmore = new int[n];
        //离散化
        int[] clone = rating.clone();
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(clone);
        for (int i = 0, index = 1; i < n; i++) {
            map.put(clone[i], index++);
        }
        for (int i = 0; i < n; i++) {
            int x=map.get(rating[i]);
            iless[i]=query(x-1);
            imore[i]=query(n)-query(x);
            add(x,1);
        }
        t=new int[n+1];
        for (int i = n-1; i >= 0; i--) {
            int x=map.get(rating[i]);
            kless[i]=query(x-1);
            kmore[i]=query(n)-query(x);
            add(x,1);
        }
        for (int i = 0; i < n; i++) {
            cnt+=iless[i]*kmore[i]+imore[i]*kless[i];
        }
        return cnt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
