package Algorithms.BinarySearch;

/**
 * 153. Find Minimum in Rotated Sorted Array
 */
public class LeetCode_0153 {
    public int findMin(int[] nums){
        int l = 0;
        int r = nums.length-1;
        while(l < r){
            int mid = l+(r-l)/2;
            if(nums[mid] <= nums[r]){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return nums[r];
    }

    public static void main(String[] args) {
        LeetCode_0153 test = new LeetCode_0153();
        int[] nums = {3,4,5,1,2};
        System.out.println(test.findMin(nums));
    }
}
