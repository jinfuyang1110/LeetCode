package com.example.hellodocker;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yjf
 * @date 2021/11/15
 * @description
 */
public class MapSum {
    private final  Map<String,Integer> map =new HashMap<>();
    public MapSum() {
    }

    public void insert(String key, int val) {
        map.put(key,val);
    }

    public int sum(String prefix) {
        int sum=0;
        for (String s : map.keySet()) {
            if (s.startsWith(prefix)){
                sum+=map.get(s);
            }
        }
      return sum;

    }
}
