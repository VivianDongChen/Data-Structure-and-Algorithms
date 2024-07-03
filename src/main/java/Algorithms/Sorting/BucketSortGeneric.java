package Algorithms.Sorting;

import Algorithms.Sorting.ComparisonBasedSorting.InsertionSort;
import DataStructure.Array.DynamicArray;

import java.util.Arrays;

/**
 * 桶排序（改进版）
 * - 让数据在桶里分布的更均匀
 * - 同时考虑到空间问题
 */
public class BucketSortGeneric {

    public static void main(String[] args) {
        int[] ages = {9,0,5,1,3,2,4,6,8,7};
        System.out.println(Arrays.toString(ages));
        sort(ages, 3);
        System.out.println(Arrays.toString(ages));
    }

    /*
      0  0,1,2
      1  3,4,5
      2  6,7,8
      3  9

     */
    public static void sort(int[] ages, int range){   //加入参数range，可以优化空间的占用
        //1. 计算出最大最小值
        int max = ages[0];
        int min = ages[0];
        for (int i = 1; i < ages.length; i++) {
            if(ages[i] > max){
                max = ages[i];
            }
            if(ages[i] < min){
                min = ages[i];
            }
        }
        //2. 准备桶（根据最大最小值计算需要多少个桶）
        DynamicArray[] buckets = new DynamicArray[(max - min)/range + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new DynamicArray();
        }
        //3. 放入年龄数据
        for(int age : ages){
            buckets[(age - min)/range] .addLast(age);  // 计算应该放入哪个桶
        }

        int k = 0;
        for(DynamicArray bucket : buckets){
//            System.out.println(Arrays.toString(bucket.array()));
            //4. 排序桶内元素
            int[] array = bucket.array();
            InsertionSort.sort(array);
            System.out.println(Arrays.toString(array));
            //5. 把每个桶排序好的内容，依次放入原数组
            for(int v : array){
                ages[k++] = v;
            }
        }
    }
}
