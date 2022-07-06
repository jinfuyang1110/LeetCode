package com.example.hellodocker.leetCode.leetcode.editor.cn;
//「力扣挑战赛」中 `N*M` 大小的自行车炫技赛场的场地由一片连绵起伏的上下坡组成，场地的高度值记录于二维数组 `terrain` 中，场地的减速值记录于二
//维数组 `obstacle` 中。
//- 若选手骑着自行车从高度为 `h1` 且减速值为 `o1` 的位置到高度为 `h2` 且减速值为 `o2` 的相邻位置（上下左右四个方向），速度变化值为 
//`h1-h2-o2`（负值减速，正值增速）。
//
//选手初始位于坐标 `position` 处且初始速度为 1，请问选手可以刚好到其他哪些位置时速度依旧为 1。请以二维数组形式返回这些位置。若有多个位置则按行
//坐标升序排列，若有多个位置行坐标相同则按列坐标升序排列。
//
//**注意：** 骑行过程中速度不能为零或负值
//
//**示例 1：**
//> 输入：`position = [0,0], terrain = [[0,0],[0,0]], obstacle = [[0,0],[0,0]]`
//> 
//> 输出：`[[0,1],[1,0],[1,1]]`
//> 
//> 解释：
//> 由于当前场地属于平地，根据上面的规则，选手从`[0,0]`的位置出发都能刚好在其他处的位置速度为 1。
//
//**示例 2：**
//> 输入：`position = [1,1], terrain = [[5,0],[0,6]], obstacle = [[0,6],[7,0]]`
//> 
//> 输出：`[[0,1]]`
//> 
//> 解释：
//> 选手从 `[1,1]` 处的位置出发，到 `[0,1]` 处的位置时恰好速度为 1。
//
//
//**提示：**
//- `n == terrain.length == obstacle.length`
//- `m == terrain[i].length == obstacle[i].length`
//- `1 <= n <= 100`
//- `1 <= m <= 100`
//- `0 <= terrain[i][j], obstacle[i][j] <= 100`
//- `position.length == 2`
//- `0 <= position[0] < n`
//- `0 <= position[1] < m` Related Topics 深度优先搜索 广度优先搜索 记忆化搜索 数组 动态规划 矩阵 👍 9 👎
// 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] bicycleYard(int[] position, int[][] terrain, int[][] obstacle) {
        int m=terrain.length,n=terrain[0].length;
        int[][]dirs={{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][][] visited=new boolean[m][n][102];
        visited[position[0]][position[1]][1]=true;
        Deque<int[]> deque=new ArrayDeque<>();
        deque.add(new int[]{position[0],position[1],1});
        while (!deque.isEmpty()){
            int[] cur = deque.pop();
            for (int[] dir : dirs) {
                int x=cur[0]+dir[0];
                int y=cur[1]+dir[1];
                if (x>=0&&x<m&&y>=0&&y<n){
                    int s=cur[2]+terrain[cur[0]][cur[1]]-terrain[x][y]-obstacle[x][y];
                    if (s>0&&!visited[x][y][s]){
                        visited[x][y][s]=true;
                        deque.add(new int[]{x,y,s});
                    }
                }
            }
        }
        visited[position[0]][position[1]][1]=false;
        List<int[]> list=new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j][1]) list.add(new int[]{i,j});
            }
        }
        int[][]res=new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            res[i]=list.get(i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
