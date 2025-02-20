package Algorithms.Kadane;

/**
 * Kadane’s Algorithm is an efficient algorithm to find the maximum sum contiguous subarray in a given array of numbers.
 */
public class KadaneAlgorithm {
    public static int maxSubArray(int[] nums) {
        int max_sum = nums[0];  // Initialize max_sum to the first element
        int current_sum = nums[0];  // Current sum starts with the first element

        for (int i = 1; i < nums.length; i++) {
            current_sum = Math.max(nums[i], current_sum + nums[i]); // Extend or restart subarray
            max_sum = Math.max(max_sum, current_sum); // Update max_sum if needed
        }
        return max_sum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum: " + maxSubArray(nums));  // Output: 6
    }
}
