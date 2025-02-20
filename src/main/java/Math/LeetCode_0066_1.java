package Math;

import java.util.Arrays;

//key issue: handle the edge case

public class LeetCode_0066_1 {
    public int[] plusOne(int[] digits){
        int l = digits.length;
        int carry = 1;
        for(int i = l-1; i >= 0; i--){
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
            if(carry == 0){
                return digits;
            }
        }
        int[] num = new int[l+1];
        num[0] = 1;
        return num;
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

