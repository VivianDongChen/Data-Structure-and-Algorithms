package DataStructure.BinarySearchTree;

/**
 * 33. Search in Rotated Sorted Array
 * 起点和终点计算出来的中间点将array分为两部分，一部分是sorted， 一部分unsorted
 * - sorted那部分按照传统的BS操作；
 * - unsorted那部分继续之前的步骤；
 */
public class LeetCode_0033 {
    public int search(int[] nums, int target){
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {        //left part is sorted, right part is unsorted
                if (nums[start] <= target && target < nums[mid]) {       //target locates in the left sorted part
                    end = mid - 1;
                } else {               // target locates in the right unsorted part
                    start = mid + 1;
                }
            } else {         //right part is sorted, left part is unsorted
                if (nums[mid] < target && target <= nums[end]) {       //target locates in the right sorted part
                    start = mid + 1;
                } else {                // target locates in the left unsorted part
                    end = mid - 1;
                }
            }
        }
        return nums[start] == target ? start : -1;
    }

    public static void main(String[] args) {
        LeetCode_0033 test = new LeetCode_0033();
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(test.search(nums, target));
    }
}
