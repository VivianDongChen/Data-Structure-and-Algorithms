package Algorithms.Sorting;

import java.util.Arrays;

/**
 * 根据另一个数组次序排序 前提：
 * 1. 元素值均 >= 0  < 1000
 * 2. 两个数组长度 <= 1000
 * 计数排序
 */
public class LeetCode1122RelativeSortArray {
    public int[] relativeSortArray(int[] arr1, int[] arr2){
        int[] count = new int[1001];
        for(int i : arr1){
            count[i]++;
        }
        /*
            int[] arr1 = {3,2,1,2,2,1,2,5,4};
            int[] arr2 = {2,3,1};

            count:
             2 4 1 1 1
             1 2 3 4 5

            原始count排序：
             1 1 2 2 2 2 3 4 5

            要求按照arr2排序：
            2 2 2 2 3 1 1 4 5

         */
        int[] result = new int[arr1.length];
        int k = 0;
        for(int i : arr2){   //检查arr2的索引
            while(count[i] > 0){
                result[k++] = i;
                count[i]--;
            }
        }
        for (int i = 0; i < count.length; i++) {
            while(count[i] > 0){
                result[k++] = i;
                count[i]--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {3,2,1,2,2,1,2,5,4};
        int[] arr2 = {2,3,1};

        LeetCode1122RelativeSortArray test = new LeetCode1122RelativeSortArray();
        System.out.println(Arrays.toString(test.relativeSortArray(arr1,arr2)));

    }

}
