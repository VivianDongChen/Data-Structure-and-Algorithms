package Algorithms.DynamicProgrammming;

/**
 * 1137. N-th Tribonacci Number
 */
public class LeetCode_1137 {
    public int tribonacci(int n) {
        int[] dp = new int[n + 1];

        //n<=3时，单独处理，提高效率，避免越界
        if(n == 0){return 0;}
        if(n == 1){return 1;}
        if(n == 2){return 1;}

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        LeetCode_1137 test = new LeetCode_1137();
        System.out.println(test.tribonacci(4)); //expected：4
        System.out.println(test.tribonacci(25));   //expected: 1389537
    }
}
