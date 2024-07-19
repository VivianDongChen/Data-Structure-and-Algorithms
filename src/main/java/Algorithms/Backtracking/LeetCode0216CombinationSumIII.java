package Algorithms.Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合之和
 * - 数字1 ～9
 * - K个数（类似77题的k）
 * - 和为n (类似39题的target） 改成target
 * - 每个数字最多使用一次
 */
public record LeetCode0216CombinationSumIII() {
    static List<List<Integer>> combinationSum(int k, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(1, target, k, new LinkedList<>(), result);
        return result;
    }

    static int count = 0;  //记录递归次数，以验证剪枝的效率


    static void dfs(int start, int target, int k, LinkedList<Integer> stack, List<List<Integer>> result){
        count++;

        if(target == 0 && stack.size() == k){
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = start; i <= 9 ; i++) {
            if(k - stack .size() > 9 - i + 1){  //剪枝： 需要的元素个数大于剩余的元素个数（这个条件减少的次数比较少）
                continue;
            }
            if(target < i){        //剪枝：剩余数额target小于现在的值
                continue;
            }
            if(stack.size() == k){     //剪枝：stack的数量已经够了，但是剩余数额target还是不为零， 就不需要进一步递归了
                continue;
            }
            stack.push(i);
            dfs(i+1, target - i, k, stack, result);
            stack.pop();
        }

    }

    public static void main(String[] args) {
//        List<List<Integer>> lists = combinationSum(3, 7);
        List<List<Integer>> lists = combinationSum(2, 18); // 没有解
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
        System.out.println(count);
    }
}
