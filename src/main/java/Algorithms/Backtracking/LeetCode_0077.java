package Algorithms.Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合
 */
public class LeetCode_0077 {

    static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(1, n, k, new LinkedList<>(), result);
        return result;
    }

    /**
     *
     * @param start 起始处理数字
     * @param n
     * @param k
     * @param stack
     * @param result
     */
    static void dfs(int start, int n , int k, LinkedList<Integer> stack, List<List<Integer>> result){
        if(stack.size() == k){  //找到一种组合
            result.add(new ArrayList<>(stack));
            return;
        }
        //i= 1，2，3，4
        for (int i = start; i <= n ; i++) {
            //剪枝
            // k -stack.size() : 还需要几个数字
            // n - i + 1 : 目前手里有几个数字
            if(k - stack .size() > n - i + 1){
                continue;
            }
            stack.push(i);  // i = 1
            dfs(i+1, n, k, stack, result);
            stack.pop();   // i = 1 回溯
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> lists = combine(4, 3);
        for(List<Integer> list: lists){
            System.out.println(list);
        }

        /*
            n   数字范围 1 ~ 4
            k   数字个数

            12 = 21（不考虑每个数字的顺序）
            13
            14
            23
            24
            34
         */
    }
}
