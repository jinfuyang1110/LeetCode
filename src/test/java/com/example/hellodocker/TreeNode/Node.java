package com.example.hellodocker.TreeNode;


import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author yjf
 * @date 2022/1/24
 * @description
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {
    }
    public Node(Node child,Node left,Node prev){
        this.child=child;
        this.prev=prev;
        this.left=left;
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

class Solution {
    Node per, head;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        head.left = per;
        per.right = head;
        return head;
    }

    void dfs(Node cur) {
        if (cur != null) {
            dfs(cur.left);
            if (per != null) per.right = cur;
            else head = cur;
            cur.left = per;
            per = cur;
            dfs(cur.right);
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            q1.add(nums1[i]);
        }
        for (int i = 0; i < n; i++) {
            q2.add(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            if (q1.isEmpty()){
                nums1[i]=q2.poll();
            }else
            if (q2.isEmpty()){
                nums1[i]=q1.poll();
            }else {
                nums1[i]=q1.peek()>q2.peek()?q2.poll():q1.poll();
            }
        }
    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists =new ArrayList<>();
        if (numRows==0) return lists;
        lists.add(new ArrayList<Integer>(){{add(1);}
        });
        for (int i = 1; i < numRows; i++) {
            List<Integer> list =new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i; j++) {
                list.add(lists.get(i).get(j-1)+lists.get(i).get(j));
            }
            list.add(1);
        }
        return lists;
    }
}
