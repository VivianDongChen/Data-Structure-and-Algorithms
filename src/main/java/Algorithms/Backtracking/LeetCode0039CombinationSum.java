package Algorithms.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合之和
 */
public class LeetCode0039CombinationSum {
    static List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, candidates, target, new LinkedList<>(), result);
        return result;
    }

    static void dfs(int start, int[] candidates, int target, LinkedList<Integer> stack, List<List<Integer>> result){

        if(target == 0){
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i < candidates.length; i++) {

            int candidate = candidates[i];
            //剪枝
            if(target < candidate){
                continue;
            }
            stack.push(candidate);
            dfs(i, candidates,target - candidate, stack, result);
            stack.pop();
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
