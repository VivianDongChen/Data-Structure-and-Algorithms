package Algorithms.BinarySearch;

import java.sql.SQLOutput;

public class LeetCode_374 {
    int pick;

    /**
     *  The number pick is within 1 to n.
     */
    public LeetCode_374(int pick) {
        this.pick = pick;
    }

    /**
     * Guess the pick number from 1 to n using binary search.
     *
     * @param n The upper bound of the range to guess from.
     * @return The correct guessed number.
     */
    public int guessNumber(int n){
        int l = 1;  // Lower bound
        int r = n;  // Upper bound

        while (l <= r) {
            // calculate the middle value
            int mid = l + (r - l) / 2;

            int result = guess(mid); // Call guess once and reuse the result
            if (result == 0) {
                return mid; // Correct guess
            } else if (result == -1) {
                r = mid - 1; // The pick number is smaller
            } else {
                l = mid + 1; // The pick number is larger
            }
        }

        return -1; // Should never reach here if input is valid
    }

    /**
     * Helper function to compare the guessed number with the picked number.
     * @param num The number to guess.
     * @return 0 if the guess is correct, -1 if the guess is too high, 1 if too low.
     */
    private int guess(int num) {
        if (num == pick) {
            return 0; // Correct guess
        } else if (num > pick) {
            return -1; // Guess is too high
        } else {
            return 1; // Guess is too low
        }
    }

    public static void main(String[] args) {
        LeetCode_374 test1 = new LeetCode_374(6);
        System.out.println(test1.guessNumber(10)); // expected: 6

        LeetCode_374 test2 = new LeetCode_374(1);
        System.out.println(test2.guessNumber(1));   // expected: 1

        LeetCode_374 test3 = new LeetCode_374(1);
        System.out.println(test2.guessNumber(2));    //expected: 1
    }
}
