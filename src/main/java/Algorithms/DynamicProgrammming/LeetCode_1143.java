package Algorithms.DynamicProgrammming;

import java.util.Arrays;

/**
 * 最长公共子序列 - 动态规划
 */
public class LeetCode_1143 {

     /*
              a  b  c  x  y  z
           0  0  0  0  0  0  0
        a  0  1  1  1  1  1  1
        b  0  1  2  2  2  2  2
        x  0  1  2  2  3  3  3
        y  0  1  2  2  3  4  4
        z  0  1  2  2  3  4  5

        相同字符
            找到上一行上一列数值（对角线数组）+1
        不同字符
            max(上一行, 上一列)
     */

    /**
     * 解法: 二维数组
     * @param text1 字符串1
     * @param text2 字符串2
     * @return 最长公共子序列
     */
    public int longestCommonSubsequence(String text1, String text2){
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1]; //将行和列都比数组长度多一个，这样方便处理i-1和j-1；
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)){      //两个数组要从索引0开始检查
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        print(dp, text2, text1);

        return dp[m][n];

    }

    public static void main(String[] args) {
        LeetCode_1143 test = new LeetCode_1143();
        System.out.println(test.longestCommonSubsequence("abxyz", "abcxyz"));
        System.out.println(test.longestCommonSubsequence("ba", "yby"));
    }

    /**
     * 打印二维数组
     * @param dp 二维数组
     * @param a 字符串1 （列）
     * @param b 字符串2 （行）
     */
    static void print(int[][] dp, String a, String b) {
        System.out.println("-".repeat(23));
        Object[] array = a.chars().mapToObj(i -> String.valueOf((char) i)).toArray();
        System.out.printf("     " + "%2s ".repeat(a.length()) + "%n", array);
        System.out.printf("     " + "%2s ".repeat(a.length()) + "%n", a.chars().mapToObj(i -> "0").toArray());
        for (int i = 0; i < b.length(); i++) {
            int[] d = dp[i + 1];
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(b.charAt(i) + " " + "%2d ".repeat(d.length) + "%n", array);
        }
    }
}
