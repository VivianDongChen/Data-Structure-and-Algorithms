package Algorithms.BinarySearch;

/**
 * LeetCode 162: Find Peak Element
 * Time Complexity:  O(logn)
 */
public class LeetCode_162 {
    public int findPeakElement(int[] nums) {
        if(nums.length == 1) return 0; // single element

        int n = nums.length;

        // check if 0th/n-1th index is the peak element
        if(nums[0] > nums[1]) return 0;
        if(nums[n-1] > nums[n-2]) return n-1;

        // search the remaining array
        int l = 1;
        int r = n-2;

        while(l <= r) {
            int mid = l + (r - l)/2;
            if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) {
                return mid;
            } else if(nums[mid] < nums[mid-1]){  //说明左侧存在峰值
                r = mid - 1;
            } else if(nums[mid] < nums[mid+1]){   //说明右侧存在峰值
                l = mid + 1;
            }
        }
        return -1; // dummy return statement
    }

    public static void main(String[] args) {
        LeetCode_162 test = new LeetCode_162();
        System.out.println(test.findPeakElement(new int[]{1,2,3,1}));   //expected: 2
        System.out.println(test.findPeakElement(new int[]{1,2,1,2,1}));    //expected: 1
    }


}
