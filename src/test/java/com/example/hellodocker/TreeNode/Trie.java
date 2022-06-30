package com.example.hellodocker.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yjf
 * @date 2022/3/1
 * @description
 */
public class Trie {
    Trie[] children;
    boolean isEnd;
    /** Initialize your data structure here. */
    public Trie() {
        children=new Trie[26];
        isEnd=false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node=this;
        for (int i = 0; i < word.length(); i++) {
            int index=word.charAt(i)-'a';
            if (node.children[index]==null) node.children[index]=new Trie();
            node=node.children[index];
        }
        node.isEnd=true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchWord(word);
        return node != null && node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchWord(prefix);
        return node!=null;
    }
    public Trie searchWord(String word){
        Trie node=this;
        for (int i = 0; i < word.length(); i++) {
            int index=word.charAt(i)-'a';
            if (node.children[index]==null) return null;
            node=node.children[index];
        }
        return node;
    }
}
