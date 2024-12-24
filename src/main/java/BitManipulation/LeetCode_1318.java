package BitManipulation;

import com.sun.jdi.PathSearchingVirtualMachine;

/**
 * 1318. Minimum Flips to Make a OR b Equal to c
 * OR 操作是一种逻辑运算，用于判断两个输入中是否至少有一个是 1。如果有一个是 1，结果就是 1；如果两个都是 0，结果就是 0。
 * How Bitwise OR Works:
 * - Convert the numbers to their binary representation.
 * - Perform a bitwise OR on each pair of bits.
 * - Convert the resulting binary number back to decimal.
 * 在这个问题中，flips 是指将二进制位从 0 转为 1 或从 1 转为 0 的操作。
 */
public class LeetCode_1318 {

    public int minFlips(int a, int b, int c) {
        int res = 0;
        while(a > 0 || b > 0 || c > 0) {
            int bitA = a & 1;  //取a对应的二进制的最后一位
            int bitB = b & 1;
            int bitC = c & 1;

            if(bitC == 0) {
                res += bitA + bitB;
            }else{
                if (bitA == 0 && bitB == 0) {
                    res++;
                }
            }

            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode_1318 test = new LeetCode_1318();
        System.out.println(test.minFlips(2, 6, 5));  //expected: 3
        System.out.println(test.minFlips(4, 2, 7));  //expected: 1
        System.out.println(test.minFlips(1, 2, 3));  //expected: 0
    }




}
