package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
//
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 922 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int n=s.length(),m=p.length();
        int[]a=new int[26];
        int[]b=new int[26];
        //输入: s = "cbaebabacd", p = "abc"
        //输出: [0,6]
        List<Integer> list =new ArrayList<>();
        for (int i = 0; i < m; i++) {
            a[p.charAt(i)-'a']++;
        }
        for (int i = 0,j=0; i < n; i++) {
            while (j<n&&j-i+1<=m){
                b[s.charAt(j++)-'a']++;
            }
            if (ok(a,b))list.add(i);
            b[s.charAt(i)-'a']--;
        }
        return list;
    }
    boolean ok(int[] a,int[] b){
        int n=a.length;
        for (int i = 0; i < n; i++) {
            if (a[i]!=b[i]) return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
