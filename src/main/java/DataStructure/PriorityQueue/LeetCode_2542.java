package DataStructure.PriorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode 2542. Maximum Subsequence Score
 * <p>
 * Given two integer arrays nums1 and nums2, and an integer k, this function finds the maximum score
 * for a subsequence of length k. The score of a subsequence is defined as the sum of its elements
 * in nums1 multiplied by the minimum element in the corresponding subsequence in nums2.
 */
public class LeetCode_2542 {
    /**
     * n == nums1.length == nums2.length
     * 1 <= n <= 105
     * 0 <= nums1[i], nums2[j] <= 105
     * 1 <= k <= n
     */
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] pairs = new int[n][2]; // Create an array of pairs combining nums2 and nums1
        for (int i = 0; i < n; ++i) {
            pairs[i] = new int[]{nums2[i], nums1[i]};
        }

        // Sort the pairs based on nums2 values in descending order
        Arrays.sort(pairs, (a, b) -> Integer.compare(b[0], a[0]));

        // Min-heap to keep track of smallest nums1 elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long maxScore = 0, currentSum = 0;

        // Iterate through the sorted pairs
        for (int[] pair : pairs) {
            minHeap.add(pair[1]); // Add nums1 element to the heap
            currentSum += pair[1]; // Update the sum of selected nums1 elements

            // If the heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                currentSum -= minHeap.poll();
            }

            // Calculate the score when the heap size equals k
            if (minHeap.size() == k) {
                maxScore = Math.max(maxScore, currentSum * pair[0]);
            }
        }

        return maxScore;
    }


    public static void main(String[] args) {
        LeetCode_2542 test = new LeetCode_2542();
        int[] nums1 = new int[]{1,3,3,2};
        int[] nums2 = new int[]{2,1,3,4};
        int k = 3;
        System.out.println(test.maxScore(nums1, nums2, k));   //expected: 12
    }
}


