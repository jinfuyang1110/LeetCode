package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给你一个 rows x cols 大小的矩形披萨和一个整数 k ，矩形包含两种字符： 'A' （表示苹果）和 '.' （表示空白格子）。你需要切披萨 k-1
// 次，得到 k 块披萨并送给别人。 
//
// 切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平
//地切，那么需要把上面的部分送给一个人。在切完最后一刀后，需要把剩下来的一块送给最后一个人。 
//
// 请你返回确保每一块披萨包含 至少 一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：pizza = ["A..","AAA","..."], k = 3
//输出：3 
//解释：上图展示了三种切披萨的方案。注意每一块披萨都至少包含一个苹果。
// 
//
// 示例 2： 
//
// 输入：pizza = ["A..","AA.","..."], k = 3
//输出：1
// 
//
// 示例 3： 
//
// 输入：pizza = ["A..","A..","..."], k = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= rows, cols <= 50 
// rows == pizza.length 
// cols == pizza[i].length 
// 1 <= k <= 10 
// pizza 只包含字符 'A' 和 '.' 。 
// 
// Related Topics 记忆化搜索 数组 动态规划 矩阵 👍 59 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Map<String, Integer> map = new HashMap<>();
    int mod = (int) 1e9 + 7;

    public int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length();
        return get("0,0," + (m-1) + "," + (n-1) + "," + k-1,pizza);
    }

    int get(String key, String[] pizza) {
        if (!map.containsKey(key)) {
            String[] nums = key.split(",");
            int[] cur = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (nums[i].equals("0")) continue;
                cur[i]=Integer.parseInt(nums[i]);
            }
            int ways = 0;
            if (cur[4] != 0) {
                //横切
                for (int k = cur[0]; k < cur[2]; k++) {
                    //是否能切
                    boolean flag = false;
                    for (int l = cur[0]; l <= k; l++) {
                        for (int i = cur[1]; i <= cur[3]; i++) {
                            if (pizza[l].charAt(i) == 'A') {
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (flag)
                        ways = (ways + get((k + 1) + "," + cur[1] + "," + cur[2] + "," + cur[3] + "," + (cur[4] - 1),pizza)) % mod;
                }
                //纵切
                for (int i = cur[1]; i < cur[3]; i++) {
                    boolean flag = false;
                    for (int j = cur[0]; j <= cur[2]; j++) {
                        for (int k = cur[1]; k <= i; k++) {
                            if (pizza[k].charAt(j) == 'A') {
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (flag)
                        ways = (ways + get(cur[0] + "," + (i + 1) + "," + cur[2] + "," + cur[3] + "," + (cur[4] - 1),pizza)) % mod;
                }
            }else w
            map.put(key, ways);
        }
        return map.get(key);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
