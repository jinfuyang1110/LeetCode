package com.example.hellodocker.TreeNode;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author yjf
 * @date 2022/1/24
 * @description
 */
public class MedianFinder {
    private PriorityQueue<Integer> small;
    private PriorityQueue<Integer> big;
    /** initialize your data structure here. */
    public MedianFinder() {
        //小顶堆
        small =new PriorityQueue<>();
        //大顶堆
        big =new PriorityQueue<>((a,b)->(b-a));
    }

    public void addNum(int num) {
        if (small.size()==big.size()){
            small.add(num);
            big.add(small.poll());
        }else {
            big.add(num);
            small.add(big.poll());
        }
    }

    public double findMedian() {
        if (small.size()!=big.size()){
            return (double) big.peek();
        }else {
            return (double) (big.peek()+small.peek())/2;
        }
    }
}
