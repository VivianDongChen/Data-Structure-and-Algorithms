package com.itheima.algorithms.recursion;

import java.util.Arrays;

/**
 * 递归冒泡排序
 *  - 将数组划分成两部分[0...j][j...a.length-1]
 *  - 左边[0...j]是未排序的
 *  - 右边[j...a.length-1]是已排序部分
 *  - 未排序区间内，相邻的两个元素比较，如果前一个大于后一个，则交换位置
 */
public class RecursionBubbleSort {

    //递归冒泡排序基础版
    public static void sort(int[] a){
        bubble(a,a.length-1);
    }

    //j:未排序区域的最右侧边界
    private static void bubble(int[] a, int j){

        if(j == 0){
            return;
        }
        for (int i = 0; i < j; i++) {
            if(a[i] > a[i+1]){
                int t = a[i];
                a[i] = a[i+1];
                a[i+1] = t;
            }

        }
        bubble(a,j-1);

    }

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        bubble(a,a.length - 1);
        System.out.println(Arrays.toString(a));

    }



    //递归冒泡排序改进版
    public static void sort1(int[] a){
        bubble1(a,a.length-1);
    }

    //改进版：对未排序区域的边界设置一个变量x,这样就不用对已经排好序的再进行比较了,减少不必要的递归
    private static void bubble1(int[] a, int j){

        int x = 0;

        if(j == 0){
            return;
        }
        for (int i = 0; i < j; i++) {
            if(a[i] > a[i+1]){
                int t = a[i];
                a[i] = a[i+1];
                a[i+1] = t;
                x = i; //最后一次交换的i就是未排序区域的边界
            }

        }
        bubble(a,x);

    }


}
