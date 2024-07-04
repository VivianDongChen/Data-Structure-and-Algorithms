package Algorithms.Sorting;

import Algorithms.Sorting.NonComparisonBasedSorting.BucketSortGeneric;

import java.util.Arrays;

/**
 * 解法1: 桶排序
 * 弊端：桶的个数过多，有可能超出内存限制
 */
public class LeetCode0164MaximumGap1 {
    public int maximumGap(int[] nums){
        //1. 处理特殊情况
        if(nums.length < 2){
            return 0;
        }
        //2. 桶排序
        BucketSortGeneric.sort(nums,1);
        System.out.println(Arrays.toString(nums));
        //3. 寻找最大差值
        int r = 0;
        for (int i = 1; i < nums.length; i++) {
            r = Math.max(r, nums[i]- nums[i-1]);
        }
        return r;
    }

    public static void main(String[] args) {
//        int[] nums = {9,1,3,5};
        int[] nums = {1, 10000000};  //弊端：桶的个数过多，有可能超出内存限制
        int r = new LeetCode0164MaximumGap1().maximumGap(nums);
        System.out.println(r);
    }
}
