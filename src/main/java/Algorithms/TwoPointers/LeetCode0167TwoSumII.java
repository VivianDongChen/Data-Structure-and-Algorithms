package Algorithms.TwoPointers;

import java.util.Arrays;

/**
 * 两数之和II - 两个指针
 * - 数组已经排好序
 * - 空间占用必须是O（1）
 * - 有且只有一个答案
 */
public class LeetCode0167TwoSumII {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }

    /*
        思路
         - 两个指针 i 和 j 分别指向最左侧和最右侧的数字
         - 它俩指向的数字和与 target 相比
            - 小于 target i++ 向右找
            - 大于 target j-- 向左找
            - 等于 target 找到
     */

    static public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        while(i < j){
            int sum = numbers[i] + numbers[j];
            if(sum == target){
                break;
            } else if (sum >target){达到。

                j--;
            }else{
                i++;
            }
        }
        return new int[]{i+1,j+1};
    }
}
