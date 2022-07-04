package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。 
//
// 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 10⁴ 。 
//
// 
//
// 示例 1： 
//
// 
//输入：expression = "2-1-1"
//输出：[0,2]
//解释：
//((2-1)-1) = 0 
//(2-(1-1)) = 2
// 
//
// 示例 2： 
//
//
//输入：expression = "2*3-4*5"
//输出：[-34,-14,-10,-10,10]
//解释：
//(2*(3-(4*5))) = -34 
//((2*3)-(4*5)) = -14 
//((2*(3-4))*5) = -10 
//(2*(/*5)) = -10
//(((2*3)-4)*5) = 10
// 
//
// 
//
// 提示： 
//
// 
// 1 <= expression.length <= 20 
// expression 由数字和算符 '+'、'-' 和 '*' 组成。 
// 输入表达式中的所有整数值在范围 [0, 99] 
// 
// Related Topics 递归 记忆化搜索 数学 字符串 动态规划 👍 627 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        int n=expression.length();
        List<Integer> nums=new ArrayList<>();
        List<Character> ops=new ArrayList<>();
        List<Integer> res=new ArrayList<>();
        int a=0;
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)){
                a=a*10+c-'0';
            }else {
                nums.add(a);
                a=0;
                ops.add(c);
            }
        }
        nums.add(a);
        int s=nums.size();
        //dp[i][j] 区间[i,j] 结果数 dp[i,j]=dp[i,k]+dp[k+1,j] 枚举k;
        List<Integer>[][]dp=new List[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                dp[i][j]=new ArrayList<>();
            }
        }
        for (int i = s-1; i>=0; i--) {
            for (int j = i; j <s; j++) {
                List<Integer> list = dp[i][j];
                if (i==j) list.add(nums.get(i));
                else {
                    for (int k = i; k <j; k++) {
                        List<Integer> one = dp[i][k];
                        List<Integer> two = dp[k + 1][j];
                        Character c = ops.get(k);
                        for (Integer per : one) {
                            for (Integer be : two) {
                                int ans;
                                if (c=='+') ans=per+be;
                                else if (c=='-') ans=per-be;
                                else ans=per*be;
                                list.add(ans);
                            }
                        }
                    }
                }
            }
        }

        return  dp[0][s-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
