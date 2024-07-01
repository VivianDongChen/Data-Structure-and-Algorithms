package Algorithms.Sorting;

import java.util.Arrays;

/**
 * 冒泡排序（非递归实现）
 * 最好：O（n)  最坏： O(n^2)   平均：O(n^2)   空间：O（1）
 */
public class BubbleSort {
    private static void bubble(int[] a){
        int j = a.length -1;
        do {
            int x = 0;
            for (int i = 0; i < j; i++) {
                if (a[i] > a[i + 1]) {
                    int t = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = t;
                    x = i;
                }
            }
            j = x;
        } while (j != 0);

    }

    public static void main(String[] args) {
        int[] a = {6,5,4,3,2,1};
        System.out.println(Arrays.toString(a));
        bubble(a);
        System.out.println(Arrays.toString(a));
    }
}
