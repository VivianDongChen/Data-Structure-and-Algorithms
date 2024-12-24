package Algorithms.DynamicProgrammming;

import java.util.Arrays;

/**
 * 338. Counting Bits
 */
public class LeetCode_0338 {

    /**
     * Time Complexity: O(n log n)
     */
    public int[] countBits1(int n) {
        int[] count = new int[n + 1];
        for(int i = 0; i<=n; i++){
            int sum = 0;
            int num = i;
            while(num != 0){
                sum += num % 2;
                num = num / 2;
            }
            count[i] = sum;
        }
        return count;
    }

    /**
     * DP - Time Complexity：O(n)
     * 使用 i & 1 来判断当前数字 i 的最低位是否为 1。结合右移操作，可以高效地计算一个整数的二进制中 1 的个数。
     * - >> ：右移操作符，i >> 1相当于将 i 除以 2，去掉二进制表示的最低位。
     * - & : 按位与（&）是位运算的一种。它对两个整数的二进制表示逐位进行比较，结果按照以下规则生成：
     *       如果两个二进制位都为 1，结果为 1；否则，结果为 0。
     * >> 和 & 属于位运算符（bitwise operators），它们作用于数据的二进制位（bit）
     */
    public int[] countBits2(int n) {

        int[] count = new int[n + 1];
        count[0] = 0;
        for(int i = 1; i<=n; i++){
            count[i] = count[i >> 1] + (i & 1);
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode_0338 test = new LeetCode_0338();
        System.out.println(Arrays.toString(test.countBits1(0)));
        System.out.println(Arrays.toString(test.countBits1(2)));
        System.out.println(Arrays.toString(test.countBits1(5)));
        System.out.println("--------------");
        System.out.println(Arrays.toString(test.countBits2(0)));
        System.out.println(Arrays.toString(test.countBits2(2)));
        System.out.println(Arrays.toString(test.countBits2(5)));
    }

}
