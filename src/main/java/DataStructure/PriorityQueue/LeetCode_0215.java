package DataStructure.PriorityQueue;

import java.util.PriorityQueue;

//kth largest Element in an Array
public class LeetCode_0215 {
    public int findKthLargest(int[] nums, int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for(int num : nums){
            minHeap.offer(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        LeetCode_0215 solver = new LeetCode_0215();

        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        int result1 = solver.findKthLargest(nums1, k1);
        System.out.println("Test Case 1: " + result1);  // Expected: 5

        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        int result2 = solver.findKthLargest(nums2, k2);
        System.out.println("Test Case 2: " + result2);  // Expected: 4

        int[] nums3 = {1};
        int k3 = 1;
        int result3 = solver.findKthLargest(nums3, k3);
        System.out.println("Test Case 3: " + result3);  // Expected: 1
    }
}
