package com.example.hellodocker.Template;

import java.util.List;

/**
 * @author yjf
 * @date 2022/3/8
 * @description 二分模板
 */
public class dichotomy {
    //寻找大于等于t
    int left(int[] arr, int t){
        int l=0,r=arr.length-1;
        while (l<=r){
            int m=l+(r-l>>1);
            if (arr[m]<t)l=m+1;
            else r=m-1;
        }
        return l;
    }
    //寻找小于等于t
    int right(int[]arr,int t){
        int l=0,r=arr.length-1;
        while (l<=r){
            int m=l+(r-l>>1);
            if (arr[m]<=t)l=m+1;
            else r=m-1;
        }
        return r;
    }
}
