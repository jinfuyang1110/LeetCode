package com.example.hellodocker.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yjf
 * @date 2022/3/2
 * @description
 */
public class MapSum {
    TrieNode root;

    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        char[] chars = key.toCharArray();
        TrieNode cur = root;
        for (char c : chars) {
            if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        cur.cost = val;
    }

    public int sum(String prefix) {
        char[] chars = prefix.toCharArray();
        TrieNode cur = root;
        for (char c : chars) {
            if (cur.children[c - 'a'] == null) return 0;
            cur = cur.children[c - 'a'];
        }
        return getCost(cur);
    }

    int getCost(TrieNode root) {
        int cost= root.cost;
        for(TrieNode cur: root.children){
            if (cur!=null) cost+=getCost(cur);
        }
        return cost;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int cost;
    }
}
