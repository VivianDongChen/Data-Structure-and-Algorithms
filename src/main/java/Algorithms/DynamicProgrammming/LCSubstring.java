package Algorithms.DynamicProgrammming;

import java.util.Arrays;

/**
 * 最长公共子串 - 动态规划
 */
public class LCSubstring {
    /*
                i   t   h   e   i   m   a
            t   0   1   0   0   0   0   0
            h   0   0   2   0   0   0   0
            e   0   0   0   3   0   0   0
            m   0   0   0   0   0   1   0
            a   0   0   0   0   0   0   2

            if(相同字符) {
                dp[i][j] = dp[i-1][j-1] + 1
            } else {
                dp[i][j] = 0
            }
         */
    /**
     * 解法1: 二维数组
     * @param a 字符串1
     * @param b 字符串2
     * @return 最长公共字串的个数
     */
    static int lcs1(String a, String b) {
        int max = 0;
        int[][] dp = new int[b.length()][a.length()];
        for (int i = 0; i < b.length(); i++) {
            for (int j = 0; j < a.length(); j++) {
                if(a.charAt(j) == b.charAt(i)) {
                    if(i == 0 || j == 0){   //第一行或第一列遇到相同的直接赋值为1
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                } else {
                    dp[i][j] = 0;
                }
                max = Math. max(max, dp[i][j]);
            }
        }
        print(dp,a,b);
        return max;
    }

    /**
     * 解法2: 一维数组
     * @param a 字符串1
     * @param b 字符串2
     * @return 最长公共字串的个数
     */
    static int lcs2(String a, String b) {
        int max = 0;
        int[] dp = new int[a.length()];
        for (int i = 0; i < b.length(); i++) {
            for (int j = a.length() - 1; j >= 0; j--) {
                if(a.charAt(j) == b.charAt(i)) {
                    if(j == 0){
                       dp[j] = 1;
                    }else{
                        dp[j] = dp[j-1] + 1;
                    }
                }else{
                    dp[j] = 0;
                }
                max = Math. max(max, dp[j]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return max;
    }




    public static void main(String[] args) {
        System.out.println(lcs2("itheima", "thema"));
    }

    /**
     * 打印二维数组
     * @param dp 二维数组
     * @param a  列
     * @param b  行
     */
    static void print(int[][] dp, String a, String b) {
        System.out.println("-".repeat(23));
        Object[] array = a.chars().mapToObj(i -> String.valueOf((char) i)).toArray();
        System.out.printf("  " + "%2s ".repeat(a.length()) + "%n", array);
        for (int i = 0; i < b.length(); i++) {
            int[] d = dp[i];
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(b.charAt(i) + " " + "%2d ".repeat(d.length) + "%n", array);
        }
    }

}
