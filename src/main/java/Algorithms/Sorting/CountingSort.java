package Algorithms.Sorting;

import java.util.Arrays;

/**
 * 计数排序 （可以处理<0的元素）
 * 要点：
 * 1. 让原始数组的最小值映射到count[0], 最大值映射到count最右侧
 * 2. 原始数组的元素 - 最小值 = count的索引
 * 时间：2N + K  优于比较排序的nlogn
 * 在元素范围k不太大的情况下，计数排序可以在线性时间内完成排序，比O(nlogn) 的基于比较的排序算法更快。
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] a = {5,1,1,3,0, -1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /*
       {5,1,1,3,0,-1}

        count数组：
       [1，1, 2, 0, 1, 0, 1]   值
        0  1  2  3  4  5  6    索引
       -1  0  1  2  3  4  5    原数组的值（映射到索引，最小值映射到0）

       [-1 0 1 1 3 5]

     */
    public static void sort(int[] a){
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if(a[i] > max){
                max = a[i];
            }
            if(a[i] < min){
                min = a[i];
            }
        }
        int[] count = new int[max - min + 1];

        for(int v : a){   //v 原始数组的元素
            count[v - min]++;
        }

        System.out.println(Arrays.toString(count));
        int k = 0;
        for (int i = 0; i < count.length; i++) {
            //i + min 代表原始数组元素，count[i]代表原始数组元素出现的次数；
            while(count[i] > 0){
                a[k++] = i + min;
                count[i]--;
            }
        }
    }

}
