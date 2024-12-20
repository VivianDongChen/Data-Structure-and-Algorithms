package Algorithms.SlidingWindow;

public class LeetCode_0209 {
    /**
     * 使用Sliding Window，O(n)
     */
    public int minSubArrayLen(int target, int[] nums){
        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
        while (j < nums.length){
            sum += nums[j];
            while (sum >= target){
                min = Math.min(min, j - i + 1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
