package DataStructure.PriorityQueue;

import java.util.PriorityQueue;

/**
 * LeetCode 2462: Total Cost to Hire K Workers
 * <p>
 * This function calculates the minimum total cost to hire exactly `k` workers from an array of costs.
 * The hiring process considers `candidates` workers from either end of the array. At each step,
 * the worker with the lowest cost is selected from either end, and their cost is added to the total.
 */
public class LeetCode_2462 {

    public long totalCost(int[] costs, int k, int candidates) {
        // 2 pointers represent positions in the costs array from
        // which workers are selected to fill the two priority queues (pq1 and pq2).
        int i = 0; // Pointer moving from the start of the array.
        int j = costs.length - 1; // Pointer moving from the end of the array.

        // Min-heaps for the left and right candidates.
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();

        long totalCost = 0;

        // Process `k` workers.
        while (k-- > 0) {
            // Add workers from the left side to pq1 until it reaches the required size or overlaps（重叠）with the right side.
            // When i > j, it means all workers between the left pointer (i) and the right pointer (j) have already been
            // compared or added to the heaps, so there's no need to process further.
            while (pq1.size() < candidates && i <= j) {
                pq1.offer(costs[i++]);
            }
            // Add workers from the right side to pq2 until it reaches the required size or overlaps with the left side.
            while (pq2.size() < candidates && i <= j) {
                pq2.offer(costs[j--]);
            }

            // Compare the smallest elements from both heaps. If one heap is empty, it is ignored.
            int leftMin = pq1.isEmpty() ? Integer.MAX_VALUE : pq1.peek();
            int rightMin = pq2.isEmpty() ? Integer.MAX_VALUE : pq2.peek();

            // Choose the smaller cost. In case of a tie（平局）, prefer the left heap.
            if (leftMin <= rightMin) {
                totalCost += leftMin;
                pq1.poll();
            } else {
                totalCost += rightMin;
                pq2.poll();
            }
        }
        return totalCost;
    }

    public static void main(String[] args) {
        LeetCode_2462 test = new LeetCode_2462();
        System.out.println(test.totalCost(new int[]{17,12,10,2,7,2,11,20,8}, 3, 4));   //expected: 11q
    }
}

