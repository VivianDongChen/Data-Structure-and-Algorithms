package Algorithms.Kadane;

public class LeetCode_0053 {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        for(int i = 1; i <nums.length; i++){
            currentSum = Math.max(currentSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum,currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        LeetCode_0053 test = new LeetCode_0053();
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(test.maxSubArray(nums));
    }
}
