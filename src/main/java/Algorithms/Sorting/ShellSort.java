package Algorithms.Sorting;

import java.util.Arrays;

/**
 * 希尔排序--改良版的插入排序， 每个元素交换的次数更少
 * 最好：O（nlogn)  最坏： O(n^2)   平均：O(nlogn)   空间：O（1）
 */
public class ShellSort {
    public static void sort1(int[] a){
        // gap = length/2\, /2....1
//        for (int gap = a.length/2; gap >= 1; gap = gap/2) {
        for (int gap = a.length >> 1; gap >= 1; gap = gap >> 1) {
            for (int low = gap; low < a.length; low++) {
                //每一次交换相当于一次InsertionSort
                int t = a[low];
                int i = low - gap;
                while(i >= 0 && a[i] > t){
                    a[i+gap] = a[i];
                    i -= gap;
                }

                if(i != low - gap){
                    a[i+gap] = t;
                }
            }
        }

    }

    public static void sort2(int[] a){
        for (int gap = a.length >> 1; gap >= 1; gap = gap >> 1) {
            for (int low = gap; low < a.length; low++) {
                if(a[low - gap] > a[low]){
                    int t = a[low];
                    a[low] = a[low -gap];
                    a[low-gap] = t;
                }
            }
        }

    }


    public static void main(String[] args) {
        int[] a = {9,3,7,2,5,8,1,4,6};
        System.out.println(Arrays.toString(a));
        sort2(a);
        System.out.println(Arrays.toString(a));
    }
}
