package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给你一个整数数组 nums，请你将该数组升序排列。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,2,3,1]
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：nums = [5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -5 * 10⁴ <= nums[i] <= 5 * 10⁴ 
// 
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 👍 604 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] sortArray(int[] nums) {
        //桶排序
        int min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
        for (int num : nums) {
            min=Math.min(min,num);
            max=Math.max(max,num);
        }
        int n=max-min+1,index=0;
        int[]ans=new int[n];
        for (int num : nums) {
            ans[num-min]++;
        }
        for (int i = 0; i < nums.length; i++) {
            while (ans[index]<=0) index++;
            nums[i]=min+index;
            ans[index]--;
        }
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
