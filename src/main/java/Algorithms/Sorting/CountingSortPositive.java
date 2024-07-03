package Algorithms.Sorting;

import java.util.Arrays;

/**
 * 计数排序（只处理>=0的元素）
 * 要点：
 * 1. 找到最大值，创建一个大小为最大值+1的count数组
 * 2. count数组的索引对应原始数组的元素，用来统计该元素的出现次数
 * 3. 遍历count数组，根据count数组的索引（即原始数组的元素）以及出现的次数，生成排序后内容
 * 关键点：count数组的索引是已经排好序的。
 * 前提：待排序元素是>=0的整数，且最大值不能太大。
 */
public class CountingSortPositive {

    public static void main(String[] args) {
        int[] a = {5,1,1,3,0};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /*
       {5,1,1,3,0}

        count数组：
       [1, 2, 0, 1, 0, 1]   值
        0  1  2  3  4  5   索引

       [0 1 1 3 5]

     */
    public static void sort(int[] a){
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if(a[i] > max){
                max = a[i];
            }
        }
        int[] count = new int[max + 1];

        for(int v : a){   //v 原始数组的元素
            count[v]++;
        }

//        System.out.println(Arrays.toString(count));
        int k = 0;
        for (int i = 0; i < count.length; i++) {
            //i代表原始数组元素，count[i]代表原始数组元素出现的次数；
            while(count[i] > 0){
                a[k++] = i;
                count[i]--;
            }
        }
    }

}
