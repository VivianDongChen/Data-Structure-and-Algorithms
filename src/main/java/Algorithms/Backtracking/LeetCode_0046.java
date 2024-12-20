package Algorithms.Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列 - 回溯
 */
public class LeetCode_0046 {
    static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, new boolean[nums.length], new LinkedList<>(), result);
        return result;
    }

    static void dfs(int[] nums, boolean[] visited, LinkedList<Integer> stack, List<List<Integer>> result){
        if(stack.size() == nums.length){
            result.add(new ArrayList<>(stack));
            return;
        }
        //遍历nums数组，发现没有被使用的数字，则将其标记为使用，并加入stack
        for (int i = 0; i < nums.length; i++) {
            // [false, false, false]   ()
            if(!visited[i]){
                stack.push(nums[i]);  //(1)
                visited[i] = true;   //[true, false, false]
                dfs(nums, visited, stack, result);
                visited[i] = false;   //[false, false, false]
                stack.pop();          //()
            }

        }
    }
    public static void main(String[] args) {
        List<List<Integer>> permute = permute(new int[]{1, 2, 3});
        for (List<Integer> list : permute) {
            System.out.println(list);
        }
    }
}
