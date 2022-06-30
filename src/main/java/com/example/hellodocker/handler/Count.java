package com.example.hellodocker.handler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yjf
 * @date 2022/6/23
 * @description
 */
public class Count {
    int n;
    int[] t;

    int lowbit(int x) {
        return x & -x;
    }

    public void add(int index, int val) {
        for (int i = index; i <= n; i += lowbit(i)) {
            t[i] += val;
        }
    }

    int query(int index) {
        int res = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            res += t[i];
        }
        return res;
    }

    public int numTeams(int[] rating) {
        n = rating.length;
        t = new int[n + 1];
        int cnt = 0;
        int[] iless = new int[n];
        int[] imore = new int[n];
        int[] kless = new int[n];
        int[] kmore = new int[n];
        //离散化
        int[] clone = rating.clone();
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(clone);
        for (int i = 0, index = 1; i < n; i++) {
            map.put(clone[i], index++);
        }
        for (int i = 0; i < n; i++) {
            int x = map.get(rating[i]);
            iless[i] = query(x - 1);
            imore[i] = query(n) - query(x);
            add(x, 1);
        }
        t = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int x = map.get(rating[i]);
            kless[i] = query(x - 1);
            kmore[i] = query(n) - query(x);
            add(x, 1);
        }
        for (int i = 0; i < n; i++) {
            cnt += iless[i] * kmore[i] + imore[i] * kless[i];
        }
        return cnt;
    }

    public static void main(String[] args) {
        Count s = new Count();
        s.numTeams(new int[]{1,2,3,4});
    }
}
