package Algorithms.Sorting;

import Algorithms.Sorting.ComparisonBasedSorting.InsertionSort;
import DataStructure.Array.DynamicArray;

import java.util.Arrays;

/**
 * 解法3: 桶排序(改进版2)
 *
 * 求最大间距， 桶间元素间距较大，桶内元素间距较小，求最大间距相当于求最大桶间元素间距， 相当于求前一个桶的最大元素与后一个桶最小元素的间距
 * 有没有可能桶内元素间距比桶间元素间距大？例如：
 * [10,19]
 * [25]
 * 只要保证有空桶，就能保证桶间元素间距大（桶个数比元素个数多）： ？？？这里的逻辑不太明白
 * [10]
 * [19，25]
 *
 *
 *            计算桶个数                       期望桶的个数（比元素个数多）
 *           （max - min) / range + 1         nums.length + 1
 *
 *           （max -min) / range + 1 = nums.length + 1
 *            range = (max -min) / nums.length
 */
public class LeetCode0164MaximumGap4 {
    public int maximumGap(int[] nums){
        //1. 处理特殊情况
        if(nums.length < 2){
            return 0;
        }
        //2. 桶排序
        //2.1 准备桶
        int max = nums[0];
        int min = nums[0];
        for (int i1 = 1; i1 < nums.length; i1++) {
            if(nums[i1] > max){
                max = nums[i1];
            }
            if(nums[i1] < min){
                min = nums[i1];
            }
        }

        int range = Math.max(1,(max - min) / nums.length);  //让桶的个数比元素多一个
        Pair[] buckets = new Pair[(max - min) / range + 1];

        //2.2 放入数据
        for(int i : nums){
             int idx = (i - min) / range;
            if(buckets[idx] == null){
                buckets[idx] = new Pair();

            }
            buckets[idx] .add(i);
        }

        for (Pair bucket : buckets) {
            System.out.println(bucket);
        }

        /*
            [12,10] 0
            [22,20] 1
            [30,30] 2
            null    3
            [40,40] 4
         */

        //3. 寻找最大差值
        int r = 0;
        int LastMax = buckets[0].max;
        for (int i = 1; i < buckets.length; i++) {
            Pair bucket = buckets[i];
            if (bucket != null){
                r = Math.max(r, bucket.min - LastMax);
                LastMax = bucket.max;
            }
        }
        return r;
    }

    //数据范围：0 <= num[i] <=1000000000
    static class Pair{
        int max = 0;
        int min = 1000000000;

        void add(int v){  //桶内最大最小值
            max = Math.max(v, max);
            min = Math.min(v, min);
        }

        @Override
        public String toString(){
            return "[" + max + "," + min + "]";
        }
    }

    public static void main(String[] args) {
//        int[] nums = {10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200,11,12,21,22};
        int[] nums = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 220};
        int r = new LeetCode0164MaximumGap4().maximumGap(nums);
        System.out.println(r);
    }
}
