package HackerRank;

public class Amazon_GetDataDependenceSum {
    public static long getDataDependenceSum(long n) {
        long sum = 0;
        long k = 1;

        while (k <= n) {
            long x = n / k;  // 计算当前 floor(n / k)
            long k2 = n / x; // 找到 x 这个值的最后出现位置

            sum += x;  // 只加一次，不重复计算
            k = k2 + 1; // 跳到下一个区间
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(getDataDependenceSum(13)); // 输出 29
        System.out.println(getDataDependenceSum(1)); // 输出 1
        System.out.println(getDataDependenceSum(5)); // 输出 8
    }
}
