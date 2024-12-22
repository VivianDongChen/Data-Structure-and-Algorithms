package Algorithms.BinarySearch;

import java.sql.SQLOutput;
import java.util.Arrays;

/**
 * LeetCode 2300: Successful Pairs of Spells and Potions
 * <p>
 * This class provides a method to calculate the number of successful pairs of spells and potions.
 * A spell and a potion form a successful pair if their product is greater than or equal to a given
 * threshold (success). The method uses binary search for efficiency.
 */
public class LeetCode_2300 {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];

        // Sort potions to enable binary search
        Arrays.sort(potions);

        // Iterate through each spell and find the number of successful pairs
        for (int i = 0; i < n; i++) {
            int spell = spells[i];
            int left = 0;
            int right = m - 1;
            // Binary search to find the first potion that meets the success condition (i.e., spell * potion >= success).
            // The goal is to find the smallest potion where the condition is satisfied.
            while (left <= right) {
                int mid = left + (right - left) / 2;
                long product = (long) spell * potions[mid];
                if (product >= success) {    // If the product is greater than or equal to success, this potion meets the requirement.
                    right = mid - 1;  // Narrow the search range by adjusting the right pointer to search for a smaller potion.
                } else {      // If the product is less than success, move the left pointer to search for larger values.
                    left = mid + 1;
                }
            }
            // After the while loop, left points to the first potion that meets the success condition.
            // All potions from index `left` to the end of the array satisfy the condition.
            pairs[i] = m - left;
        }
        return pairs;
    }

    public static void main(String[] args) {

        int[] spells = new int[]{5,1,3};
        int[] potions = new int[]{1,2,3,4,5};
        int success = 7;

        LeetCode_2300 test = new LeetCode_2300();
        System.out.println(Arrays.toString(test.successfulPairs(spells, potions, success)));  //expected: [4,0,3]
    }

}
