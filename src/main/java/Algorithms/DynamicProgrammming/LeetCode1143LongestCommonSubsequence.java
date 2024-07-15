package Algorithms.DynamicProgrammming;

/**
 * 最长公共子序列 - 动态规划
 */
public class LeetCode1143LongestCommonSubsequence {

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
}
