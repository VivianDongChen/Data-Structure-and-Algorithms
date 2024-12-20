package Algorithms.DynamicProgrammming;

/**
 * 打家劫舍
 */
public class LeetCode_0198 {

    /*

        价值     0   1   2   3   4   房间

                0   0   0   0   0
        0(7)    7   0   0   0   0
        1(2)    7   7   0   0   0
        2(9)    2   7   11  0   0
        3(3)    2   7   11  11  0
        4(1)    2   7   11  11  12

        dp[4] = dp[2]+1 = 12
        dp[3] = max(dp[1]+3, dp[2]) = max(10, 11) = 11

        dp[i] = max(dp[i-2]+value, dp[i-1])
     */

    public int rob(int[] houses){
        if(houses.length == 1){
            return houses[0];
        }

        int[] dp = new int[houses.length];
        dp[0] = houses[0];
        dp[1] = Math.max(dp[0], houses[1]);
        for (int i = 2; i < houses.length; i++) {
            dp[i] = Math.max(dp[i-2] + houses[i], dp[i-1]);
        }
        return dp[houses.length - 1];

    }


    public static void main(String[] args) {
        LeetCode_0198 code = new LeetCode_0198();
        System.out.println(code.rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(code.rob(new int[]{2, 1, 1, 2}));
    }

}
