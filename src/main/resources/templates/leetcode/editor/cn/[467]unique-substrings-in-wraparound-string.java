package com.example.hellodocker.leetCode.leetcode.editor.cn;
//把字符串 s 看作 "abcdefghijklmnopqrstuvwxyz" 的无限环绕字符串，所以 s 看起来是这样的： 
//
// 
// "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." 。 
// 
//
// 现在给定另一个字符串 p 。返回 s 中 不同 的 p 的 非空子串 的数量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：p = "a"
//输出：1
//解释：字符串 s 中只有 p 的一个 "a" 子字符。
// 
//
// 示例 2： 
//
// 
//输入：p = "cac"
//输出：2
//解释：字符串 s 中只有 p 的两个子串 ("a", "c") 。
// 
//
// 示例 3： 
//
// zabcdefghij
//输入：p = "zab"
//输出：6
//解释：在字符串 s 中有 p 的六个子串 ("z", "a", "b", "za", "ab", "zab") 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= p.length <= 10⁵ 
// p 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 336 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findSubstringInWraproundString(String p) {
        int[] dp =new int[26];
        int n=p.length(),cnt=0;
        for (int i = 0; i < n; i++) {
            if (i>0&&(p.charAt(i)-p.charAt(i-1)+26)%26==1)cnt++;
            else cnt=1;
            dp[p.charAt(i)-'a']=Math.max(dp[p.charAt(i)-'a'],cnt);
        }
        return Arrays.stream(dp).sum();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
