package Algorithms.DynamicProgrammming;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成 - DP
 * 弊端：性能不好，有4层循环
 */
public class LeetCode_0022 {

    public List<String> generateParenthesis(int n){
        ArrayList<String>[] dp = new ArrayList[n+1];
        dp[0] = new ArrayList<>(List.of(""));
        dp[1] = new ArrayList<>(List.of("()"));
//        dp[2] = (()), ()()
//        dp[3] = ()(()), ()()(), (())(), ((())),(()())
        for (int j = 2; j < n+1; j++) {
            dp[j] = new ArrayList<>();
            for (int i = 0; i < j; i++) {
//                dp[i]: 对应的集合是内层要嵌套的括号的集合
//                dp[i-1-j]; 对应的集合是平级要拼接的括号的集合
                for(String k1: dp[i]){
                    for(String k2: dp[j-1-i]){
                       dp[j].add("("+k1+")"+ k2);
                    }
                }
            }

        }
        return dp[n];
    }

    public static void main(String[] args) {
        LeetCode_0022 code = new LeetCode_0022();
        System.out.println(code.generateParenthesis(3));
    }
}
