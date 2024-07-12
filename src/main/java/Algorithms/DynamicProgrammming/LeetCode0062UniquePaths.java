package Algorithms.DynamicProgrammming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 独特路径问题 - 动态规划算法
 */
public class LeetCode0062UniquePaths {

    public static void main(String[] args) {
        int count = new LeetCode0062UniquePaths().uniquePaths2(3,7);
        System.out.println(count);
    }

    /**
     * 方法1: 使用二维数组存放dp
     *
     *           0  1  2  3  4  5  6
     *
     *      0    1  1  1  1  1  1  1
     *      1    1  2  3  4  5  6  7
     *      2    1  3  6 10 15 21 28
     *
     * @param m 行数
     * @param n 列数
     * @return
     */
    public int uniquePaths1(int m, int n) {
        //定义一个二维数组
        int[][] dp = new int[m][n];
        //初始化第0列的值
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        //初始化第0行的值
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        //填充数据
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        print(dp);
        return dp[m - 1][n - 1];
    }

    /**
     * 打印二维数组
     * @param dp 二维数组
     */
    static void print(int[][] dp) {
        System.out.println("-".repeat(20));       //a separator line consisting of 20 dashes (-)
        //System.out.printf 不支持直接格式化 int[]，因此需要将其转换为包装类型 Integer[] 或更通用的 Object[]
        Object[] array = IntStream.range(0, dp[0].length).boxed().toArray();  //创建了一个包含从 0 到 dp[0].length -1 的整数数组
        System.out.printf(("%2d ".repeat(dp[0].length)) + "%n", array);    //按照格式打印array，其中每个整数至少占用2个字符宽度，并在每个整数之间添加一个空格。
        //遍历每一行
        for (int[] d : dp) {
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(("%2d ".repeat(d.length)) + "%n", array);
        }
    }

    /**
     * 方法2: 使用一纬数组存放dp
     *           0  1  2  3  4  5  6
     *      0    1  3  6 10 15 21 28
     *      = 本身 + 左边的值
     *
     * @param m 行数
     * @param n 列数
     * @return
     */
    public int uniquePaths2(int m, int n) {
        //定义一个一维数组
        int[] dp = new int[n];
        //初始化数组（原来的二维数组的第0行数据）
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }

        for (int i = 1; i < m; i++) {  //第i行变成第i轮
            dp[0] = 1;
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n-1];
    }

}