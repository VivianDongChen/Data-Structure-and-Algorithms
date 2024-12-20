package Algorithms.Sorting;

import Algorithms.Sorting.ComparisonBasedSorting.InsertionSort;
import DataStructure.Array.DynamicArray;

import java.util.Arrays;

/**
 * 解法1: 桶排序
 * 弊端：桶的个数过多，有可能超出内存限制
 */
public class LeetCode_0164_I {
    public int maximumGap(int[] nums){
        //1. 处理特殊情况
        if(nums.length < 2){
            return 0;
        }
        //2. 桶排序
        //加入参数range，可以优化空间的占用
        //1. 计算出最大最小值
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
        //2. 准备桶（根据最大最小值计算需要多少个桶）
        DynamicArray[] buckets = new DynamicArray[(max - min)/ 1 + 1];
        for (int i1 = 0; i1 < buckets.length; i1++) {
            buckets[i1] = new DynamicArray();
        }
        //3. 放入年龄数据
        for(int age : nums){
            buckets[(age - min)/ 1] .addLast(age);  // 计算应该放入哪个桶
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
                nums[k++] = v;
            }
        }
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
        int r = new LeetCode_0164_I().maximumGap(nums);
        System.out.println(r);
    }
}
