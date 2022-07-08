package com.example.hellodocker.leetCode.leetcode.editor.cn;
//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> 
//s2 -> ... -> sk： 
//
// 
// 每一对相邻的单词只差一个字母。 
// 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。 
// sk == endWord 
// 
//
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 
//中的 单词数目 。如果不存在这样的转换序列，返回 0 。 
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
//"log","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
//"log"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有字符串 互不相同 
// 
// Related Topics 广度优先搜索 哈希表 字符串 👍 1068 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Set<String> words;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;
        Deque<String> b = new ArrayDeque<>(), e = new ArrayDeque<>();
        Map<String,Integer> bMap=new HashMap<>(),eMap=new HashMap<>();
        b.add(beginWord);e.add(endWord);bMap.put(beginWord,1);eMap.put(endWord,1);
        while (!b.isEmpty()&&!e.isEmpty()){
            int t=-1;
            if (b.size()<=e.size()) t=update(b,bMap,eMap);
            else t=update(e,eMap,bMap);
            if (t!=-1) return t;
        }
        return 0;
    }
    int update(Deque<String> deque,Map<String,Integer> cur,Map<String,Integer> other){
        int size=deque.size();
        while (size-->0){
            String now = deque.pop();
            char[] chars = now.toCharArray();
            int n=chars.length;
            int step=cur.get(now);
            for (int i = 0; i < n; i++) {
                char temp =chars[i];
                for (int j = 0; j < 26; j++) {
                    chars[i]=(char) (j+'a');
                    String newWord = new String(chars);
                    if (words.contains(newWord)&&!cur.containsKey(newWord)){
                        if (other.containsKey(newWord)) return other.get(newWord)+step;
                        deque.add(newWord);
                        cur.put(newWord,step+1);
                    }
                }
                chars[i]=temp;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
