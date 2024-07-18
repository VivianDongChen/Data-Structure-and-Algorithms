package Algorithms.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合之和（
 * - 数组元素不可重复，
 * - 且解集不能包含重复的组合- 数组中有可能存在重复的元素
 */
public class LeetCode0040CombinationSumII {

    static List<List<Integer>> combinationSum(int[] candidates, int target){
        Arrays.sort(candidates);  //将数组排序，这样重复的元素会排在一起
        List<List<Integer>> result = new ArrayList<>();
        //加入一个布尔数组，用来记录元素是否被加入了stack， 进而区分1和1‘
        dfs(0, candidates, target, new boolean[candidates.length], new LinkedList<>(), result);
        return result;
    }

    static void dfs(int start, int[] candidates, int target, boolean[] visited, LinkedList<Integer> stack, List<List<Integer>> result){

        if(target == 0){
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i < candidates.length; i++) {

            int candidate = candidates[i];
            if(i > 0 && candidate == candidates[i-1] && !visited[i-1]){  //如果发现重复，并且1没有被加入过stack，这个时候1’不能直接加入stack
                continue;
            }
            if(target < candidate){
                continue;
            }

            stack.push(candidate);
            visited[i] = true;
            dfs(i+1, candidates,target - candidate, visited, stack, result);    //将下一个start改为i+1
            stack.pop();
            visited[i] = false;
        }

    }


    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> lists = combinationSum(candidates, 8);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
