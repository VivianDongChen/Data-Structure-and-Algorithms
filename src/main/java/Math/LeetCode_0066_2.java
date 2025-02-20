package Math;

import java.math.BigInteger;
import java.util.Arrays;

/**
 *  key Issue：
 *  How to correctly convert between different data types (e.g., int[], String, BigInteger) in Java?"
 */
public class LeetCode_0066_2 {
    public int[] plusOne(int[] digits) {
        //O(n) : use StringBuilder to convert an Array to a String. Ps: can not use Arrays.toString() which is used for printing
        StringBuilder sb = new StringBuilder();
        for(int d : digits){
            sb.append(d);
        }

        // convert String to an Integer, the Integer can be very big, so here use BigInteger.
        BigInteger num = new BigInteger(sb.toString());

        //Add operation for BigInteger, and then use toString() to convert the bigInteger to a string
        String str = num.add(BigInteger.ONE).toString();

        //O(n):Iteration to convert the string to an Array
        int[] res = new int[str.length()];
        for(int i = 0; i < str.length(); i++){
            res[i] = str.charAt(i) - '0';  //convert each digit character to integer
        }
        return res;
    }

    public static void main(String[] args) {
        int[] digits1 = {1, 2, 3};
        int[] digits2 = {9, 9, 9};
        int[] digits3 = {0};
        int[] digits4 = {8, 9, 9, 9};

        LeetCode_0066_1 test = new LeetCode_0066_1();

        System.out.println(Arrays.toString(test.plusOne(digits1))); // [1, 2, 4]
        System.out.println(Arrays.toString(test.plusOne(digits2))); // [1, 0, 0, 0]
        System.out.println(Arrays.toString(test.plusOne(digits3))); // [1]
        System.out.println(Arrays.toString(test.plusOne(digits4))); // [9, 0, 0, 0]
    }
}


