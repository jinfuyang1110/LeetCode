package com.example.hellodocker.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Eric
 * @date 2021/10/9
 * @description
 */
public class SummaryRanges {
    private List<Integer> list;

    public SummaryRanges() {
        this.list = new ArrayList();
    }

    public void addNum(int val) {
        list.add(val);
    }

    public int [][] getIntervals() {
        if (list.size()==0) return new int [][]{};
        list.sort(Integer::compareTo);
        int cur =list.get(0);
        if (list.size()==1) return new int[][]{{cur,cur}};
        List<int[]> arr = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            Integer x = list.get(i);
            Integer in = list.get(i - 1);
            if (x-in<2) continue;
            int [] a={cur, in};
            arr.add(a);
            cur=x;
        }
        int last= list.get(list.size()-1);
        if (last-list.get(list.size()-2)<2){
            arr.add(new int[]{cur, last});
        }else {
            arr.add(new int[]{last,last});
        }
        int [][]aim =new int[arr.size()][];
        for (int i = 0; i < aim.length; i++) {
            aim[i]=arr.get(i);
        }
        return aim;
    }
}

class Summary {
    public static void main(String[] args) {
        SummaryRanges ranges = new SummaryRanges();
        ranges.addNum(4);
        ranges.addNum(3);
        ranges.addNum(3);
        ranges.addNum(3);
        ranges.addNum(2);
        ranges.addNum(5);
        ranges.addNum(1);
        ranges.addNum(5);
        ranges.addNum(9);
        ranges.addNum(8);
        ranges.addNum(10);
        ranges.addNum(12);
        int[][] intervals = ranges.getIntervals();
        for (int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }
    }
}