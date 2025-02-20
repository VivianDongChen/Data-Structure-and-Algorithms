package Algorithms.BinarySearch;

public class LeetCode_0004 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if(m > n){
            return findMedianSortedArrays(nums2, nums1);
        }
        int total = m + n;
        int totalHalf = (total + 1)/2;   // total left partition size
        int left = 0;
        int right = m;
        while(left <= right){
            int half1 = left +(right - left)/2;  //left partition size of nums1
            int half2 = totalHalf - half1;    //left partition size of nums2

            int left1 = (half1 == 0) ? Integer.MIN_VALUE : nums1[half1 - 1];  //no element from nums1
            int right1 = (half1 == m) ? Integer.MAX_VALUE : nums1[half1];    //all element of nums1 in
            int left2 = (half2 == 0) ? Integer.MIN_VALUE : nums2[half2 - 1];    //no element from nums2
            int right2 = (half2 == n) ? Integer.MAX_VALUE : nums2[half2];     //all element of nums2 in

            if (right1 >= left2 && right2 >= left1){
                if(total % 2 == 1){
                    return Math.max(left1, left2);
                }else{
                    return (Math.min(right1, right2) + Math.max(left1,left2)) / 2.0;
                }
            }

            if(right2 < left1){ //nums1取值少了
                left = half1;
            }else{  //nums1取值多了
                right = half1 - 1;
            }
        }
        return -1;
    }
}
