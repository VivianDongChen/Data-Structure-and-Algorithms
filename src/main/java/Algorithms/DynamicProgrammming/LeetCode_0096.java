package Algorithms.DynamicProgrammming;

import java.util.Arrays;

/**
 * 卡特兰数（给定 n 个节点，能组成的不同二叉搜索树（BST）的个数）
 */
public class
LeetCode_0096 {
    public static void main(String[] args) {
        System.out.println(catalan(6));
    }

    static int catalan(int n){
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int j = 2; j < n + 1; j++) {   //第j个卡特兰数
            for (int i = 0; i < j; i++) {
                System.out.printf("(%d,%d)\t", i, j - 1 - i);  //拆分这个卡特兰数
                dp[j] += dp[i] * dp[j-1-i];
            }
            System.out.println();
            System.out.println(Arrays.toString(dp));
        }

        return dp[n];
    }
}


