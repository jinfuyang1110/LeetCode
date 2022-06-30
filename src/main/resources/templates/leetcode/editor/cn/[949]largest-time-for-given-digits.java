package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。 
//
// 24 小时格式为 "HH:MM" ，其中 HH 在 00 到 23 之间，MM 在 00 到 59 之间。最小的 24 小时制时间是 00:00 ，而最大
//的是 23:59 。从 00:00 （午夜）开始算起，过得越久，时间越大。 
//
// 以长度为 5 的字符串，按 "HH:MM" 格式返回答案。如果不能确定有效时间，则返回空字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [1,2,3,4]
//输出："23:41"
//解释：有效的 24 小时制时间是 "12:34"，"12:43"，"13:24"，"13:42"，"14:23"，"14:32"，"21:34"，"21:4
//3"，"23:14" 和 "23:41" 。这些时间中，"23:41" 是最大时间。
// 
//
// 示例 2： 
//
// 
//输入：arr = [5,5,5,5]
//输出：""
//解释：不存在有效的 24 小时制时间，因为 "55:55" 无效。
// 
//
// 示例 3： 
//
// 
//输入：arr = [0,0,0,0]
//输出："00:00"
// 
//
// 示例 4： 
//
// 
//输入：arr = [0,0,1,0]
//输出："10:00"
// 
//
// 
//
// 提示： 
//
// 
// arr.length == 4 
// 0 <= arr[i] <= 9 
// [0,0,0,0]
//[5,5,5,5]
//[4,2,3,5]
//[1,8,2,5]
//[1,1,6,0]
//[1,1,6,6]
// Related Topics 字符串 枚举 👍 80 👎 0


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    boolean flag;
    int[] res = new int[4];
    Set<Integer> set =new HashSet<>();
    public String largestTimeFromDigits(int[] arr) {
        Arrays.sort(arr);
        d(arr,0);
        if (flag) {
            return new StringBuilder().append(res[0])
                    .append(res[1]).append(":").append(res[2]).append(res[3])
                    .toString();
        }
        return "";
    }

    void d(int[] arr, int n) {
        if (n == 4) {
            if (isOK(res)) flag = true;
            return;
        }
        for (int i = 3; i >=0 ; i--) {
            if (set.contains(i)) continue;
            if (flag) return;
            res[n]=arr[i];
            set.add(i);
            d(arr,n+1);
            set.remove(i);
        }
    }

    boolean isOK(int[] res) {
        if (res[0] > 2
                || res[0] == 2 && res[1] > 3
                || res[2] > 5
        ) return false;
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
