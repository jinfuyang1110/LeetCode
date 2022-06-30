package com.example.hellodocker.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Eric
 * @date 2021/9/15
 * @description
 */
public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        this.right = right;
        this.left = left;
    }

    public int[] levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList() {{
            add(root);
        }};
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        int arr[] = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList() {{
            add(root);
        }};
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        return lists;
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return root;
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(root.left);
        return root;
    }

    @Test
    public void mirrorTree() {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(1);
        node.right = new TreeNode(7);
        TreeNode node1 = mirrorTree(node);
    }
}
