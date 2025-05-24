package DataStructure.PriorityQueue;

import java.util.*;

public class LeetCode_0373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k){
        List<List<Integer>> res = new ArrayList<>();

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a ->(nums1[a[0]] + nums2[a[1]])));

        for(int i = 0; i < Math.min(k, nums1.length); i++){
            minHeap.add(new int[]{i, 0});
        }

        while(!minHeap.isEmpty() && k --> 0){
            int[] indices = minHeap.poll();
            int index1 = indices[0];
            int index2 = indices[1];

            res.add(Arrays.asList(nums1[index1], nums2[index2]));
             if(index2 + 1 < nums2.length){
                 minHeap.offer(new int[]{index1, index2 + 1});
             }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode_0373 solver = new LeetCode_0373();

        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;

        List<List<Integer>> result = solver.kSmallestPairs(nums1, nums2, k);

        System.out.println("Top " + k + " pairs with smallest sums:");
        for (List<Integer> pair : result) {
            System.out.println(pair);
        }
    }
}
