package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。 
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 1 <= words.length <= 5000 
// 1 <= words[i].length <= 30 
// words[i] 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 697 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int n = words.length, m = words[0].length(), l = s.length();
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        //枚举划分可能
        for (int  k= 0; k < m; k++) {
            Map<String, Integer> cur = new HashMap<>();
            for (int i = k, j = k; i + n * m <= l; i += m) {
                while (j < l && j - i + 1 <= n * m) {
                    String sub = s.substring(j, j + m);
                    cur.put(sub, cur.getOrDefault(sub, 0) + 1);
                    j += m;
                }
                if (cur.equals(map)) res.add(i);
                String per = s.substring(i, i + m);
                if (cur.get(per) == 1) cur.remove(per);
                else cur.put(per, cur.get(per) - 1);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
