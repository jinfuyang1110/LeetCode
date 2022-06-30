package com.example.hellodocker.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yjf
 * @date 2022/3/2
 * @description
 */
public class MapSum {
    Map<String, Integer> map;

    public MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key,val);
    }

    public int sum(String prefix) {
        int res = 0;
        for (String s : map.keySet()) {
            if (s.startsWith(prefix)) {
                res += map.get(s);
            }
        }
        return res;
    }
}
