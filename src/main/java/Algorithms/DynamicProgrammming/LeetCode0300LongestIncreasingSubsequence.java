package Algorithms.DynamicProgrammming;

import java.util.Arrays;

/**
 * 最长的递增子序列 - 动态规划
 *
 */
public class LeetCode0300LongestIncreasingSubsequence {
    /*
                                    i
            j
            1       3       6       4       9

 初始值      1       3       6       4       9
           (1)     (1)     (1)     (1)      (1)

开始组合     1       13      16      14      19
                            136     134     139
                                            169
                                            1369
                                            149
                                            1349
           (1)    (2)      (3)     (3)      (4)
                                            4
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {    //内循环
                if (nums[i] > nums[j]) {  //满足了升序条件
//                    dp[i] = dp[0] + 1;
//                          = dp[1] + 1;
//                             ...
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return Arrays.stream(dp).max().getAsInt(); //返回dp中的最大值
    }

    public static void main(String[] args) {
        LeetCode0300LongestIncreasingSubsequence code = new LeetCode0300LongestIncreasingSubsequence();
        System.out.println(code.lengthOfLIS(new int[]{1, 3, 6, 4, 9}));
        System.out.println(code.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(code.lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
        //                        数组的LIS是             1 3 6 7 9 10  = 6
        //                        而包含最后一个元素的IS是  1 3 4 5 6     = 5
        //                        所以结果不是dp的最后一个元素，而是dp中的最大值
        System.out.println(code.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(code.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }
}
