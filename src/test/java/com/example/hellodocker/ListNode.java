package com.example.hellodocker;

/**
 * @author yjf
 * @date 2021/11/2
 * @description
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x, ListNode listNode) {
        val = x;
        next=listNode;
    }
    ListNode(int x) {
        val = x;
    }
}
