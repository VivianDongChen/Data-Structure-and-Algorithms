package Algorithms.DynamicProgrammming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 零钱兑换 - 动态规划
 * 凑成总金额的凑法中，需要硬币最少个数是几？
 */
public class LeetCode0322CoinChange {
    /*
    面值             0           1            2            3           4            5     总金额

   0    1           0(0)        1(1)       11(2)       111(3)      1111(4)     11111(5)
   1    2           0(0)        1(1)        2(1)        21(2)       22(2)       221(3)
   2    5           0(0)        1(1)        2(1)        21(2)       22(2)       5(1)


   装不下默认值设为最大值max:

     面值    0        1        2        3           4          5         6          7
      2     0        max      1      max + 1        2      max + 2      3       max + 3
      5     0        max      1      max + 1        2          1        3          2


     总金额❤  - 类比为背包容量
     硬币面值  - 类比为物品重量
     硬币个数  - 类比为物品价值，设每个硬币价值固定为1 ， 物品价值 = 硬币价值 * 硬币个数 = 硬币个数（求价值(个数)最小的）

     if(装得下) {
        min(上次价值(个数), 剩余容量能装下的最小价值(个数)+1)
        dp[i][j] = min(dp[i-1][j], dp[i][j-item.weight] + 1)
     } else {
        保留上次价值(个数)不变
        dp[i][j] = dp[i-1][j]
     }
     */

    /**
     * 解法1: 二维数组存放dp
     * @param coins  零钱数组
     * @param amount  总金额
     * @return
     */
    public int coinChange1(int[] coins, int amount){
        int[][] dp = new int[coins.length][amount + 1];
        //处理第一行， 以便后面使用i-1
        for (int j = 1; j < amount + 1; j++) {
            if(j >= coins[0]){
                dp[0][j] = dp[0][j-coins[0]] + 1;
            }else{
                dp[0][j] = amount + 1;  //最大值设置为amount+1（最多的硬币数也不会超过这个）
            }
        }
        for (int i = 1; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = 0; j < amount + 1; j++) {
                if(j >= coin) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-coin] + 1);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }

        }

        print(dp);

        return dp[coins.length - 1][amount] < amount + 1 ? dp[coins.length - 1][amount] : -1;

    }

    /**
     * 打印二维数组
     * @param dp 二维数组
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

    /**
     * 解法2: 一维数组存放dp
     * @param coins  零钱数组
     * @param amount  总金额
     * @return
     */
    public int coinChange2(int[] coins, int amount){
        int[] dp = new int[amount + 1];

//        for (int j = 1; j < amount + 1; j++) {
//            if(j >= coins[0]){
//                dp[j] = dp[j-coins[0]] + 1;
//            }else{
//                dp[j] = amount + 1;
//            }
//        }
//        for (int i = 1; i < coins.length; i++) {
//            int coin = coins[i];
//            for (int j = 0; j < amount +1; j++) {   //正序遍历
//                if( j >= coin) {
//                    dp[j] = Math.min(dp[j], dp[j-coin] + 1);
//                }
//            }
//        }

        //代码优化：

        //初始化数组为： 0  max  max  max  max
        Arrays.fill(dp, amount +1);
        dp[0] = 0;
        System.out.println(Arrays.toString(dp));

        for (int coin : coins){
            for (int j = 0; j < amount +1; j++) {   //正序遍历
                if( j >= coin) {
                    dp[j] = Math.min(dp[j], dp[j-coin] + 1);
                }
            }
        }

        System.out.println(Arrays.toString(dp));

        return dp[amount] < amount + 1 ? dp[amount] : -1;

    }

    public static void main(String[] args) {
        LeetCode0322CoinChange test = new LeetCode0322CoinChange();
//        int count = test.coinChange2(new int[]{5,2,1}, 5);
//        int count = test.coinChange2(new int[]{25, 10, 5, 1}, 41);
//        int count = test.coinChange2(new int[]{2}, 3);
        int count = test.coinChange2(new int[]{15, 10, 1}, 21);
        System.out.println(count);
    }
}
