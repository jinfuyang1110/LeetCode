package com.example.hellodocker.leetCode.leetcode.editor.cn;
//给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 
//endDayi 。 
//
// 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。 
//
// 请你返回你可以参加的 最大 会议数目。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：events = [[1,2],[2,3],[3,4]]
//输出：3
//解释：你可以参加所有的三个会议。
//安排会议的一种方案如上图。
//第 1 天参加第一个会议。
//第 2 天参加第二个会议。
//第 3 天参加第三个会议。
// 
//
// 示例 2： 
//
// [[1,1],[1,3],[1,1]
//输入：events= [[1,2],[2,3],[3,4],[1,2]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= events.length <= 10⁵ 
// events[i].length == 2 
// 1 <= startDayi <= endDayi <= 10⁵ 
// 
// Related Topics 贪心 数组 堆（优先队列） 👍 212 👎 0


import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxEvents(int[][] events) {
        int cnt=0;
        PriorityQueue<int[]> pq=new PriorityQueue<>((a, b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
        pq.addAll(Arrays.asList(events));
        int begin=0;
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int a=cur[0],b=cur[1];
            if (begin<=a){
                cnt++;
                begin=a+1;
            }else {
                if (begin<b) pq.add(new int[]{begin,b});
                else if (begin==b) {
                    cnt++;
                    begin++;
                }
            }
        }
        return cnt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
