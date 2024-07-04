package Algorithms.Sorting;

import java.util.ArrayList;

/**
 * 解法2: 基数排序(全部都是数字的话，准备10个桶就够了）
 * 弊端：基数排序时间复杂度O(kn)比桶排序0(n)还是差一些
 */
public class LeetCode0164MaximumGap2 {
    public int maximumGap(int[] nums){
        //1. 处理特殊情况
        if(nums.length < 2){
            return 0;
        }

        //2. 基数排序


        //2.1 准备好数组的最大值、桶
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);

        }
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new ArrayList<>();
        }
        //2.3 循环：每一轮基数排序（个位、十位、百位...) O（kn）

        /*
        如何获取一个数字的个位、十位、百位、千位...：
        - 4233 / 1 = 4233  4233 % 10 = 3
        - 4233 / 10 = 423  423 % 10 = 3
        - 4233 / 100 = 42  42 % 10 = 2
        - 4233 / 1000 = 4  4 % 10 = 4
         */

        int m = 1;
        while(m <= max){
            //2.2 一轮基数排序
            //2.2.1 数据放入桶
            for (int i: nums) {
                buckets[i / m % 10].add(i);
            }
            //2.2.2 取出来放入原数组
            int k = 0;
            for(ArrayList<Integer> bucket : buckets) {
                for (int i : bucket) {
                    nums[k++] = i;
                }
                bucket.clear(); //清空桶，以备下一轮使用
            }
             m *= 10;
        }
        //3. 寻找最大差值
        int r = 0;
        for (int i = 1; i < nums.length; i++) {
            r = Math.max(r, nums[i]- nums[i-1]);
        }
        return r;
    }

    public static void main(String[] args) {
        int[] nums = {26,16,13,11, 10000000};
        int r = new LeetCode0164MaximumGap2().maximumGap(nums);
        System.out.println(r);
    }
}
