package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [0,1]
//输出: 2
//说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。 
//
// 示例 2: 
//
// 
//输入: nums = [0,1,0]
//输出: 2
//说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// nums[i] 不是 0 就是 1 
// 
// Related Topics 数组 哈希表 前缀和 👍 569 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMaxLength(int[] nums) {
        int n=nums.length,max=0;
        int[] res=new int[n+1];
        for (int i = 1; i <= n; i++) {
            res[i]=res[i-1]+(nums[i-1]==0?-1:1);
        }
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,0);
        for (int i = 1; i <= n; i++) {
            if (map.containsKey(res[i])){
                max=Math.max(i-map.get(res[i]),max);
            }else map.put(res[i],i);
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
