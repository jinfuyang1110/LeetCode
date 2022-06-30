package com.example.hellodocker.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yjf
 * @date 2022/2/22
 * @description
 */
public class CBTInserter {
    TreeNode root;
    Deque<TreeNode> deque;

    public CBTInserter(TreeNode root) {
        this.root = root;
        deque = new LinkedList<>();
        deque.offer(root);
        while (root.left != null && root.right != null) {
            TreeNode node = deque.pop();
            deque.offer(node.left);
            deque.offer(node.right);
            root = deque.peek();
        }
    }

    public int insert(int v) {
        TreeNode node = deque.peek();
        if (node.left == null) {
            node.left = new TreeNode(v);
        } else {
            node.right = new TreeNode(v);
            deque.offer(node.left);
            deque.offer(node.right);
            deque.pop();
        }
        return node.val;
    }

    public TreeNode get_root() {
        return this.root;
    }
}
