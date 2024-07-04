package Algorithms.Sorting;

import Algorithms.Sorting.ComparisonBasedSorting.InsertionSort;
import DataStructure.Array.DynamicArray;

import java.util.Arrays;

/**
 * 解法3: 桶排序(改进版）计算出合适的range
 */
public class LeetCode0164MaximumGap3 {
    public int maximumGap(int[] nums){
        //1. 处理特殊情况
        if(nums.length < 2){
            return 0;
        }
        //2. 桶排序
        //2.1 计算出最大最小值
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
        //2.2 准备桶

        /*
           计算桶个数                       期望桶的个数
          （max - min) / range + 1         nums.length

          （max -min) / range + 1 = nums.length
           range = (max -min) / (nums.length - 1)
         */

        int range = Math.max(1,(max - min) / (nums.length - 1));  //range至少为1，避免除零异常
        DynamicArray[] buckets = new DynamicArray[(max - min) / range + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new DynamicArray();
        }
        //3. 放入数据
        for(int i : nums){
            buckets[(i - min) / range] .addLast(i);  // 计算应该放入哪个桶
        }

        int k = 0;
        for(DynamicArray bucket : buckets){
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
//        int[] nums = {1, 10000000};
        int[] nums = {1,1,1,1,1};
        int r = new LeetCode0164MaximumGap3().maximumGap(nums);
        System.out.println(r);
    }
}
