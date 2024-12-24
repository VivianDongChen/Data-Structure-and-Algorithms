package Algorithms.DynamicProgrammming;

/**
 * 790. Domino and Tromino Tiling
 */
public class LeetCode_790 {
    public int numTilings1(int n){
        long [] dp = new long[n];
        long [] dpa = new long[n];

        if(n == 0)return 0;
        if(n == 1)return 1;

        dp[0] = 1;
        dp[1] = 2;
        dpa[0] = 0;
        dpa[1] = 1;

        for(int i = 2; i < n; i++){
            dp[i] = (dp[i-1] + dp[i-2] + 2 * dpa[i-1]) % 1000000007;  //在动态规划问题中使用 mod 1000000007（或类似的大素数）主要是为了避免整数溢出并提高程序的性能和适用性。
            dpa[i] = (dpa[i-1] + dp[i-2]) % 1000000007;
        }

        return (int)dp[n-1];
    }

    /**
     * 优化
     */
    public int numTilings2(int n){

        if(n == 0)return 0;
        if(n == 1)return 1;

        long mod = 1000000007;

        long prev0 = 1;
        long prev1 = 2;
        long prev1a = 1;


        for(int i = 2; i < n; i++){
            long cur = (prev1 + prev0 + 2 * prev1a) % mod;
            long cura = (prev1a + prev0) % mod;
            prev0 = prev1;
            prev1 = cur;
            prev1a = cura;
        }

        return (int) prev1;
    }

    public static void main(String[] args) {
        LeetCode_790 test = new LeetCode_790();

        System.out.println(test.numTilings1(0));
        System.out.println(test.numTilings1(1));
        System.out.println(test.numTilings1(2));
        System.out.println(test.numTilings1(3));
        System.out.println(test.numTilings1(4));
        System.out.println("--------------------");

        System.out.println(test.numTilings2(0));
        System.out.println(test.numTilings2(1));
        System.out.println(test.numTilings2(2));
        System.out.println(test.numTilings2(3));
        System.out.println(test.numTilings2(4));
    }
}
