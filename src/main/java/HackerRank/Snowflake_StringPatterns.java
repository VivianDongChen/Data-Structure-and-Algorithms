package HackerRank;

import java.util.*;

public class Snowflake_StringPatterns {
    static final int MOD = 1_000_000_007;

    public static int calculateWays(int wordLen, int maxVowels) {
        // 定义 dp 数组
        long[][] dp = new long[wordLen + 1][maxVowels + 1];

        // 初始化：长度为 1 的单词
        dp[1][0] = 21;  // 一个辅音
        dp[1][1] = 5;   // 一个元音

        // 填充 dp 数组
        for (int i = 2; i <= wordLen; i++) {
            // 计算 dp[i][0]：最后一个字符是辅音
            long sum = 0;
            for (int j = 0; j <= maxVowels; j++) {
                sum = (sum + dp[i - 1][j]) % MOD;
            }
            dp[i][0] = sum * 21 % MOD;

            // 计算 dp[i][j]：最后 j 个字符是元音 (1 <= j <= maxVowels)
            for (int j = 1; j <= maxVowels; j++) {
                dp[i][j] = dp[i - 1][j - 1] * 5 % MOD;
            }
        }

        // 求结果：所有长度为 wordLen 的合法单词总数
        long result = 0;
        for (int j = 0; j <= maxVowels; j++) {
            result = (result + dp[wordLen][j]) % MOD;
        }

        return (int) result;
    }

    public static void main(String[] args) {
        // 示例测试
        Scanner scanner = new Scanner(System.in);
        int wordLen = scanner.nextInt();
        int maxVowels = scanner.nextInt();
        System.out.println(calculateWays(wordLen, maxVowels));
    }
}

