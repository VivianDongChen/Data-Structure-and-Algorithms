package HackerRank;

/**
 * 算法：DP
 * 时间复杂度
 * 构造字符计数表：O(n * m)（n 是字符串的数量，m 是字符串的长度）
 * 动态规划计算：O(t * m)（t 是目标字符串的长度，m 是列的数量）
 * 总的时间复杂度为：O(n∗m+t∗m)𝑂
 * 在最坏情况下，这已经是非常高效的，因为我们必须至少遍历一次所有输入字符。
 * 空间复杂度：O(t * m)（用于存储 DP 表）
 */

public class Snowflake_StringFormation {
    public int numWays(String[] words, String target) {
        final int MOD = 1_000_000_007;
        int n = words.length;    // 字符串数组中的字符串数量
        int m = words[0].length(); // 每个字符串的长度
        int tLen = target.length(); // 目标字符串的长度

        // 统计字符串的每个索引位置字母的出现次数
        int[][] charCount = new int[m][26];

        // 遍历每个字符串，统计每个索引位置字母的出现次数
        for (String word : words) {
            for (int j = 0; j < m; j++) {
                charCount[j][word.charAt(j) - 'a']++;    //每个字符串第j个索引的26个字母的出现次数
            }
        }

        // 初始化 DP 数组，dp[i][j] 表示用前 j 列字符构造目标字符串的前 i 个字符的方法数
        long[][] dp = new long[tLen + 1][m + 1];

        // 初始化：构造空字符串的方法数为 1
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 1;  // 构造目标字符串的前 0 个字符总是有 1 种方法
        }

        // 遍历目标字符串 target 的每一个字符
        for (int i = 1; i <= tLen; i++) {
            char targetChar = target.charAt(i - 1);

            // 遍历 words 的每一列
            for (int j = 1; j <= m; j++) {
                // 不选择当前列 j 时的方法数：继承前一列的值
                dp[i][j] = dp[i][j - 1];

                // 如果当前列中有目标字符 targetChar
                int charIndex = targetChar - 'a';
                if (charCount[j - 1][charIndex] > 0) {
                    // 加上选择当前列的方法数
                    dp[i][j] += dp[i - 1][j - 1] * charCount[j - 1][charIndex];
                    dp[i][j] %= MOD;  // 防止溢出
                }
            }
        }

        // 返回使用所有列构造整个目标字符串的方法数
        return (int) dp[tLen][m];
    }

    public static void main(String[] args) {
        Snowflake_StringFormation solution = new Snowflake_StringFormation();

        // 示例测试1
        String[] words1 = {"adc", "aec", "efg"};
        String target1 = "ac";
        System.out.println(solution.numWays(words1, target1));  // 输出：4

        // 示例测试2
        String[] words2 = {"valya", "lyglb", "vldoh"};
        String target2 = "val";
        System.out.println(solution.numWays(words2, target2));  // 输出：4
    }

}
