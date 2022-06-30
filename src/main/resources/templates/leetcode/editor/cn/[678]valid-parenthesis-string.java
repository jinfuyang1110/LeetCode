package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则： 
//
// 
// 任何左括号 ( 必须有相应的右括号 )。 
// 任何右括号 ) 必须有相应的左括号 ( 。 
// 左括号 ( 必须在对应的右括号之前 )。 
// * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。 
// 一个空字符串也被视为有效字符串。 
// 
//
// 示例 1: 
//
// 
//输入: "()"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "(*)"
//输出: True
// 
//
// 示例 3: 
//
// 
//输入: "*)(("
//输出: True
// 
//
// 注意: 
//
// (*()())
// 字符串大小将在 [1，100] 范围内。 
// 
// Related Topics 栈 贪心 字符串 动态规划 👍 492 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean checkValidString(String s) {
        Deque<Integer> one =new ArrayDeque<>();
        Deque<Integer> two =new ArrayDeque<>();
        char[] chars = s.toCharArray();
        int n=chars.length;
        for (int i = 0; i < n; i++) {
            char c=chars[i];
            if (c=='(') one.push(i);
            else if (c=='*') two.push(i);
            else {
                if (!one.isEmpty()) one.pop();
                else if (!two.isEmpty()) two.pop();
                else return false;
            }
        }
        while (!one.isEmpty()&&!two.isEmpty()){
            if (one.pop()>two.pop()) return false;
        }
        return one.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
