package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR)
// 符号组成。实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。 
//
// 示例 1: 
//
// 输入: s = "1^0|0|1", result = 0
//
//输出: 2
//解释: 两种可能的括号方法是
//1^(0|(0|1))
//1^((0|0)|1)
// 
//
// 示例 2: 
//
// 输入: s = "0&0&0&1^1|0", result = 1
//
//输出: 10 
//
// 提示： 
//
// 
// 运算符的数量不超过 19 个 
// 
// Related Topics 记忆化搜索 字符串 动态规划 👍 66 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Map<String, int[]> map = new HashMap<>();

    public int countEval(String s, int result) {
        int n = s.length() - 1;
        int[] res = g(0 + "," + n, s);
        if (result == 0) return res[0];
        else return res[1];
    }

    int[] g(String t, String s) {
        if (!map.containsKey(t)) {
            int l = Integer.parseInt(t.split(",")[0]);
            int r = Integer.parseInt(t.split(",")[1]);
            int[] res = new int[2];
            if (l == r) res[s.charAt(l) - '0']++;
            for (int i = l; i < r; i += 2) {
                int[] before = g(l + "," + i, s);
                int[] end = g((i + 2) + "," + r, s);
                char c = s.charAt(i + 1);
                if (c == '|') {
                    res[0] += before[0] * end[0];
                    res[1] += before[1] * end[0] + before[0] * end[1] + before[1] * end[1];
                } else if (c == '&') {
                    res[0] += before[0] * end[0] + before[1] * end[0] + before[0] * end[1];
                    res[1] += before[1] * end[1];
                } else {
                    res[0] += before[0] * end[0] + before[1] * end[1];
                    res[1] += before[0] * end[1] + before[1] * end[0];
                }
            }
        map.put(l + "," + r, res);
    }
        return map.get(t);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
