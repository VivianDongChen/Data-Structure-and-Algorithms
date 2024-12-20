package Algorithms.DynamicProgrammming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 零钱兑换II - 动态规划
 * 凑成总金额有几种凑法？
 */
public class LeetCode_0518 {


    /*
     面值    0      1      2        3        4        5         总金额-背包容量

     1      1      1      11      111      1111     11111
          （1）   （1）    （1）    （1）     （1）      （1）

     2      1      1      11       111     1111     11111
                          2         21      211      2111
                                             22       221
          （1）   （1）    （2）     （2）     （3）     （3）

     5      1      1      11       111      1111    11111
                          2         21       211     2111
                                              22      221
                                                        5
          （1）   （1）    （2）     （2）     （3）     （4）


     面值    0      1      2        3        4        5         总金额-背包容量

     2                     2                22
          （1）   （0）    （1）    （0）     （1）    （0）

     5                     2                22        5

          （1）   （0）    （1）    （0）    （1）     （1）

     面值 - 物品重量

     if(放得下)
        dp[i][j] = dp[i-1][j] + dp[i][j-coin]
     else(放不下)
        dp[i][j] = dp[i-1][j]

     */
    /**
     * 解法1: 用二维数组存放dp
     * @param coins  零钱数组
     * @param amount  零钱总额
     * @return 凑法
     */
    public int change1(int[] coins, int amount){
        int[][] dp = new int[coins.length][amount + 1];
        //初始化第一列的数据
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }
        //初始化第一行的数据
        for (int j = 1; j < amount + 1; j++) {
            if(j >= coins[0]){
                dp[0][j] = dp[0][j-coins[0]];
            }
        }
        print(dp);
        for (int i = 1; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = 1; j < amount + 1; j++) {
                if(j >= coin){
                    dp[i][j] = dp[i-1][j] + dp[i][j-coin];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        print(dp);
        return dp[coins.length - 1][amount];
    }
    /**
     * 解法2: 用一维数组存放dp
     * @param coins  零钱数组
     * @param amount  零钱总额
     * @return 凑法
     */
    public int change2(int[] coins, int amount){
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int j = 1; j < amount + 1; j++) {
            if(j >= coins[0]){
                dp[j] = dp[j-coins[0]];
            }
        }
        for (int i = 1; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = 0; j < amount + 1; j++) {
                if(j >= coin){
                    dp[j] = dp[j] + dp[j-coin];
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }



    public static void main(String[] args) {
        LeetCode_0518 test = new LeetCode_0518();
//        int count = test.change2(new int[]{1, 2, 5}, 5);
//        int count = test.change2(new int[]{2}, 3);
        int count = test.change2(new int[]{15, 10, 1}, 21);
//        int count = test.change2(new int[]{25, 10, 5, 1}, 41);
        System.out.println(count);
    }

    /**
     * 打印二维数组
     * @param dp
     */
    static void print(int[][] dp) {
        System.out.println("-".repeat(18));
        Object[] array = IntStream.range(0, dp[0].length + 1).boxed().toArray();
        System.out.printf(("%2d ".repeat(dp[0].length)) + "%n", array);
        for (int[] d : dp) {
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(("%2d ".repeat(d.length)) + "%n", array);
        }
    }
}
