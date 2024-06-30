package Algorithms.Sorting;

import java.util.Arrays;

/**
 * 插入排序（非递归实现）
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
