package Algorithms.Kadane;

public class LeetCode_0918 {
    public int maxSubarraySumCircular(int[] nums) {
        int currmax = nums[0];
        int currmin = nums[0];
        int maxsum = nums[0];
        int minsum = nums[0];
        int total = nums[0];

        for(int i = 1; i < nums.length; i++) {
            currmax = Math.max(currmax + nums[i], nums[i]);
            currmin = Math.min(currmin + nums[i], nums[i]);
            maxsum = Math.max(maxsum, currmax);
            minsum = Math.min(minsum, currmin);
            total += nums[i];
        }
        return maxsum < 0 ? maxsum : Math.max(maxsum, total-currmin);

    }

    public static void main(String[] args) {
        LeetCode_0918 test = new LeetCode_0918();
        int[] nums = new int[]{1,-2,3,-2};
        System.out.println(test.maxSubarraySumCircular(nums));
    }
}
