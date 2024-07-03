package Algorithms.Sorting.ComparisonBasedSorting;

import java.util.Arrays;

/**
 * 选择排序
 * 最好：O（n^2)  最坏： O(n^2)   平均：O(n^2)   空间：O（1）
 */
public class SelectionSort {
    public static void sort(int[] a){
        //1. 选择轮数 a.length - 1
        //2. 交换的索引位置（right）初始a.length -1， 每次递减
        for (int right = a.length-1; right > 0 ; right--) {
            int max = right;
            for (int i = 0; i < right; i++) {
                if(a[i] > a[max]){
                    max = i;
                }
            }
            if(max != right){
                swap(a,max,right);
            }

        }

    }

    private static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {6,5,4,3,2,1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
