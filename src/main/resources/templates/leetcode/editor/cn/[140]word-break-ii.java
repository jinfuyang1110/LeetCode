package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可
//能的句子。 
//
// 注意：词典中的同一个单词可能在分段中被重复使用多次。 
//
// 
//
// 示例 1： 
//
// 
//输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
//输出:["cats and dog","cat sand dog"]
// 
//
// 示例 2： 
//
// 
//输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine",
//"pineapple"]
//输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 
//输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//输出:[]
// 
//
// 
//
// 提示： 
//
// 
//
// 
// 1 <= s.length <= 20 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 10 
// s 和 wordDict[i] 仅有小写英文字母组成 
// wordDict 中所有字符串都 不同 
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 回溯 👍 611 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Map<Integer, List<List<String>>> map;
    int n;
    Set<String> set;

    public List<String> wordBreak(String s, List<String> wordDict) {
        map = new HashMap<>();
        n = s.length();
        set = new HashSet<>(wordDict);
        List<String> res=new ArrayList<>();
        List<List<String>> ans = df(s, 0);
        for (List<String> an : ans) {
            res.add(String.join(" ",an));
        }
        return res;
    }
    List<List<String>> df(String s,int start){
        if (!map.containsKey(start)){
            List<List<String>> lists=new ArrayList<>();
            if (start==n){
                lists.add(new ArrayList<>());
            }
            for (int i = start+1; i <= n; i++) {
                String word = s.substring(start, i);
                if (set.contains(word)){
                    List<List<String>> nextWords = df(s, i);
                    for (List<String> nextWord : nextWords) {
                        LinkedList<String> list=new LinkedList<>(nextWord);
                        list.addFirst(word);
                        lists.add(list);
                    }
                }
            }
            map.put(start,lists);
        }
        return map.get(start);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
