package com.example.hellodocker.Template;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author yjf
 * @date 2022/4/21
 * @description
 */
public class gcd {
    int gcd(int x,int y){
        return y==0?x: gcd(y,x%y);
    }
    @Test
    public void test() {
        System.out.println(gcd(5,1));
        System.out.println(gcd(15,3));
        System.out.println(gcd(20,10));
        System.out.println(gcd(50,25));
    }
}
class Solution {
    int[]jobs;
    int k,n;
    int min;
    public int minimumTimeRequired(int[] jobs, int k) {
        this.jobs=jobs;
        Arrays.sort(jobs);
        n=jobs.length;
        this.k=k;
        min=Integer.MAX_VALUE;
        int[] sum=new int[k];
        dfs(0,n-1,0,sum);
        return min;
    }
    void dfs(int used,int pos,int max,int[] sum){
        if (max>=min) return;
        if (pos==-1){
            min=max;
            return;
        }
        //优先空闲
        if (used<k){
            sum[used]=jobs[pos];
            dfs(used+1,pos-1,Math.max(max,sum[used]),sum);
            //回溯
            sum[used]=0;
        }
        for (int i = 0; i < used; i++) {
            if (i>0&&sum[i]==sum[i-1]) continue;
            if (i>0&&pos==n-1) return;
            sum[i]+=jobs[pos];
            dfs(used,pos-1,Math.max(sum[i],max),sum);
            sum[i]-=jobs[pos];
        }
    }
}
