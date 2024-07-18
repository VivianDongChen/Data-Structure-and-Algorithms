package Algorithms.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列II - 有重复的元素
 */
public class LeetCode0047PermutationsII {
    static List<List<Integer>> permute(int[] nums){
        Arrays.sort(nums);  //对数组进行排序，这样重复的元素就相邻了
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, new boolean[nums.length], new LinkedList<>(), result);
        return result;
    }

    static void dfs(int[] nums, boolean[] visited, LinkedList<Integer> stack, List<List<Integer>> result){
        if(stack.size() == nums.length){
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if( i > 0 && nums[i] == nums[i-1] && !visited[i-1]){  //找到重复的元素，而且之前的那个与它重复的元素没有被固定过，疑问 ：要是3个重复的元素呢？
                continue;
            }

            if(!visited[i]){
                stack.push(nums[i]);
                visited[i] = true;
                dfs(nums, visited, stack, result);
                visited[i] = false;
                stack.pop();
            }

        }
    }
    public static void main(String[] args) {
        List<List<Integer>> permute = permute(new int[]{1, 3, 1});  //一定要先固定1，再固定1‘
        for (List<Integer> list : permute) {
            System.out.println(list);
        }
    }
}
