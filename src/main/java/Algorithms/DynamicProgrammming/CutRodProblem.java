package Algorithms.DynamicProgrammming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 钢条切割问题 - 动态规划
 */
public class CutRodProblem {

    /*
        长度： 0   1   2   3   4   5   6   7   8   9   10
        价值： 0   1   5   8   9   10  17  17  20  24  30

        总长度： 4

         切割单位       0    1     2    3      4      - 总长度

            1               1    11    111   1111
                          （1）  （2）  （3）  （4）

            2               1    11    111   1111
                          （1）  （2）  （3）  （4）
                                  2     21    211
                                （5）   （6）  （7）
                                               22
                                             （10）


            3               1    11    111   1111
                          （1）  （2）  （3）  （4）
                                  2     21    211
                                （5）   （6） （17）
                                         3     22
                                       （8）  （10）
                                              31
                                             （9）

            4               1    11    111   1111
                          （1）  （2）  （3）  （4）
                                  2     21    211
                                （5）   （6）  （7）
                                         3     22
                                       （8）  （10）
                                               31
                                              （9）
                                                4
                                              （9）

        if(放得下)
            dp[i][j]=max(dp[i-1][j],当前物品价值+dp[i][j-物品重量]
        else(放不下)
            dp[i][j]=dp[i-1][j]

     */

    /**
     * 解法1: 二维数组放dp
     * @param values 钢条价值数组
     * @param n  钢条长度
     * @return
     */
    static int cut1(int[] values, int n){
        int[][] dp = new int[values.length][n+1];
        //第0行和第0列没有用到
        for (int i = 1; i < values.length; i++) {
            for (int j = 1; j < n+1; j++) {
                if(j >= i){
                    dp[i][j] = Math.max(dp[i-1][j],values[i]+dp[i][j-i]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        print(dp);

        return dp[values.length - 1][n];

    }

    /**
     * 解法2: 一维数组放dp
     * @param values 钢条价值数组
     * @param n  钢条长度
     * @return
     */
    static int cut2(int[] values, int n){
        int[]dp = new int[n+1];
        //第0行和第0列没有用到
        for (int i = 1; i < values.length; i++) {
            for (int j = 1; j < n+1; j++) {
                if(j >= i){
                    dp[j] = Math.max(dp[j],values[i]+dp[j-i]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));

        return dp[n];

    }

    public static void main(String[] args) {
        // 不同长度钢条的价值数组，数组的索引对应钢条的长度（物品重量-切割单位）
        System.out.println(cut2(new int[]{0, 1, 5, 8, 9}, 4)); //10, 17, 17, 20, 24, 30
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
