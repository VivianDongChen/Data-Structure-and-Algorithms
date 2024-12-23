package Algorithms.DynamicProgrammming;

public class LeetCode_7546 {
    /**
     * Dynamic Programming with Auxiliary Array
     * Computes the minimum cost to reach the top of the stairs using an additional array to store intermediate results.
     * Space Complexity: O(n) - due to the extra dp array of size n+1.
     */
    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i-1] , dp[i-2]);
        }
        dp[n] = Math.min(dp[n-1] , dp[n-2]);
        return dp[n];
    }

    /**
     * Dynamic Programming with Constant Space Optimization
     * Computes the minimum cost to reach the top of the stairs using two variables to store the minimum costs, reducing space usage.
     * Space Complexity: O(1) - only a constant amount of extra space is used.
     */
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int first = cost[0];
        int second = cost[1];
        for (int i = 2; i < n; i++) {
            int temp = Math.min(first,second) + cost[i];
            first = second;
            second = temp;
        }
        return Math.min(first , second);
    }

    public static void main(String[] args) {
        LeetCode_7546 test = new LeetCode_7546();
        System.out.println(test.minCostClimbingStairs1(new int[]{10,15,20}));  //expected：15
        System.out.println(test.minCostClimbingStairs2(new int[]{10,15,20}));
    }
}
