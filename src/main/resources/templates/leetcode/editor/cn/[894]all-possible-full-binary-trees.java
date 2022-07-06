package com.example.hellodocker.leetCode.leetcode.editor.cn;
//满二叉树是一类二叉树，其中每个结点恰好有 0 或 2 个子结点。 
//
// 返回包含 N 个结点的所有可能满二叉树的列表。 答案的每个元素都是一个可能树的根结点。 
//
// 答案中每个树的每个结点都必须有 node.val=0。 
//
// 你可以按任何顺序返回树的最终列表。 
//
// 
//
// 示例： 
//
// 输入：7
//输出：[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0
//,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
//解释：
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 20 
// 
// Related Topics 树 递归 记忆化搜索 动态规划 二叉树 👍 269 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    Map<Integer, Set<TreeNode>> map = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> list = new ArrayList<>();
        if ((n & 1) == 0) return list;
        list.addAll(map.get(n))
        return list;
    }

    Set<TreeNode> d(int n) {
        if (!map.containsKey(n)) {
            Set<TreeNode> set = new HashSet<>();
            if (n == 1) set.add(new TreeNode(0));
            else {
                Set<TreeNode> b = d(n - 2);
                for (TreeNode root : b) {
                    List<TreeNode> list = new ArrayList<>();
                    is(root,list);
                    for (TreeNode node : list) {
                        node.
                    }
                }
            }
            map.put(n, set);
        }
        return map.get(n);
    }

    void is(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        if (root.left == null && root.right == null) list.add(root);
        is(root.left, list);
        is(root.right, list);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
