package Algorithms.Sorting.ComparisonBasedSorting;

import java.util.Arrays;

/**
 * 插入排序（非递归实现）
 * 最好：O（n)  最坏： O(n^2)   平均：O(n^2)   空间：O（1）
 */
public class InsertionSort {
    public static void sort(int[] a){
        for (int low = 1; low < a.length; low++) {
            int t = a[low];
            int i = low - 1;
            while(i >= 0 && a[i] > t){
                a[i+1] = a[i];
                i--;
            }

            if(i != low - 1){
                a[i+1] = t;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {9,3,7,2,5,8,1,4,6};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
