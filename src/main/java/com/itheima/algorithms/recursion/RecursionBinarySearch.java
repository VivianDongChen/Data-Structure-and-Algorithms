package com.itheima.algorithms.recursion;

//递归二分查找
public class RecursionBinarySearch {

    public static int search(int[] a, int target){

        return f (a, target,0,a.length - 1);
    }

    private static int f (int[] a, int target, int i, int j){
        if(i > j){
            return -1;
        }

        int m = (i+j) >>> 1;

        if(target < a[m]){
            return f (a,target, i,m-1);
        }else if(a[m] < target){
            return f (a, target,m+1,j);
        }else{
            return m;
        }

    }

}
