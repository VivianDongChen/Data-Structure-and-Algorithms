package Algorithms.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 四数之和
 * 组合不可以有重复的，但是组合中的数字可以重复（如果原数组中有重复的数字的话），通过以下逻辑满足：
 * - 代码去重，保证了每一个阶段的数字的唯一（第一个固定的数不能重复，第二个固定的数不能重复....i不能重复，j不能重复）
 * - 但是被固定的数字可以与后面的i或者j相同，而i也可以和j相同
 */
public class LeetCode0018FourSum {

    static List<List<Integer>> fourSum(int[] nums, int target){
        long longTarget = (long) target;   //为了通过最后一个越界的测试用例，将target的类型转化为long
        Arrays.sort(nums);  //对数组进行排序，以方便去重
        List<List<Integer>> result = new ArrayList<>();
        dfs(4,0, nums.length - 1, longTarget, nums, new LinkedList<>(), result);
        return result;
    }

    static int count = 0;

    static void dfs(int n, int i, int j, long target, int[] nums, LinkedList<Integer> stack, List<List<Integer>> result){
        count++;


        if(n == 2){   //套用两数之和进行求解
            twoSum (i, j, nums, target, stack, result);
            return;
        }
        for(int k = i; k < j - (n - 2); k++){  //四数之和: k < j - 2, 三数之和 ： k < j - 1, 相当于剪枝
            //检查重复
            if(k > i && nums[k] == nums[k-1]){
                continue;
            }

//            //剪枝
//            if(j - k + 1 < n){
//                continue;
//            }

            //固定一个数字，再尝试n-1之和
            stack.push(nums[k]);
            dfs(n-1, k+1, j, target-nums[k], nums, stack, result);
            stack.pop();
        }
    }



    static void twoSum (int i, int j, int[] nums, long target, LinkedList<Integer> stack, List<List<Integer>> result)
    {

        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum > target) {
                j--;
            } else if (sum < target) {
                i++;
            } else {
                stack.push(nums[i]);
                stack.push(nums[j]);
                result.add(new ArrayList<>(stack));
                stack.pop();    //继续循环，寻找下一组组合
                stack.pop();
                i++;
                j--;
                //去重
                while (i < j && nums[i] == nums[i - 1]) {    //i指针遇到重复的，往前走，直到找到不重复的元素
                    i++;
                }
                while (i < j && nums[j] == nums[j + 1]) {    //j指针遇到重复的，往后走，直到找到不重复的元素
                    j--;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(fourSum(new int[]{-2, -1, 0, 0, 1, 1, 1, 2, 2, 2}, 0));
        System.out.println(fourSum(new int[]{2, 2, 2, 2, 2}, 8));
        System.out.println(fourSum(new int[]{0, 0, 0, 0}, 0));
        System.out.println(fourSum(new int[]{1, 2, 0}, 0));
        System.out.println(fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296));
    }
}