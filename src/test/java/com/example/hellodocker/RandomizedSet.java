package com.example.hellodocker;

import org.junit.Test;

import java.util.*;

/**
 * @author yjf
 * @date 2022/2/18
 * @description
 */
public class RandomizedSet {
    Map<Integer,Integer>map;
    List<Integer> list;
    Random r=new Random();

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        map=new HashMap<>();
        list=new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (!map.containsKey(val)){
            list.add(list.size(),val);
            map.put(val,list.size()-1);
            return true;
        }
        return false;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (map.containsKey(val)){
            int last=list.get(list.size()-1);
            int valIndex=map.get(val);
            list.set(valIndex,last);
            list.remove(list.size()-1);
            map.put(last,valIndex);
            map.remove(val);
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(r.nextInt(list.size()));
    }
    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(3);
        list.add(1,5);
    }
}
