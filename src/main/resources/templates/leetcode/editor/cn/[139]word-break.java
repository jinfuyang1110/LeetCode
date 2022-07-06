package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。 
//
// 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
// 
//
// 示例 2： 
//
// 
//输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
//     注意，你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 
//输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 300 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 20 
// s 和 wordDict[i] 仅有小写英文字母组成 
// wordDict 中的所有字符串 互不相同 
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 👍 1683 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[] res;
    int n;
    Set<String> set;
    public boolean wordBreak(String s, List<String> wordDict) {
       //记忆化回溯
        n=s.length();
        set=new HashSet<>(wordDict);
        //res[i]:i-n能否找到直接组成
        res=new int[n];
        return bfs(s,0);
    }
    int  bfs(String s,int start){
        if (res[start]!=0) return res[start];
        for (int i = start+1; i <= n; i++) {
            String word = s.substring(start, i);
            if (set.contains(word)){
                if (bfs(s,i)==1) return res[start]=1;
            }
        }
        return res[start]=-1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
