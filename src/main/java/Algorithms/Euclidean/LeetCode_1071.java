package Algorithms.Euclidean;

/**
 * 欧几里得算法（Euclidean Algorithm）
 * 欧几里得算法是一种高效计算两个非负整数最大公因数（GCD, Greatest Common Divisor）的方法。它基于以下数学性质：
 * 如果两个整数a和b的最大公因数是GCD(a,b)，那么有：GCD(a,b)=GCD(b,a%b)
 * 当b=0时，GCD(a,0)=a。
 */
public class LeetCode_1071 {
    public String gcdOfStrings(String str1, String str2) {
        if(!(str1 + str2).equals(str2 + str1)){
            return "";
        }
        //一旦确定两个字符串有公共因子（即可以通过一个公共字符串模式重复拼接生成），字符串的具体内容就不再重要，关键在于它们的长度之间的关系
        int n = gcd(str1.length(), str2.length());
        return str1.substring(0,n);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        LeetCode_1071 test = new LeetCode_1071();
        System.out.println(test.gcdOfStrings("ABCABC", "ABC"));
        System.out.println(test.gcdOfStrings("ABABABAB", "ABAB"));
        System.out.println(test.gcdOfStrings("ABAB", "ABABABAB"));
    }
}
