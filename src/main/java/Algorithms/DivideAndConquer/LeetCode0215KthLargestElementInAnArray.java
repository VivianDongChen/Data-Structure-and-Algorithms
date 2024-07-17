package Algorithms.DivideAndConquer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 求数组中第K大的元素 - 快速选择（分治）， 时间复杂度 O(n)
 * 注：比小顶堆法更高效                              疑问：但是LeetCode跑下来效率并不高？
 *
 */
public class LeetCode0215KthLargestElementInAnArray {

    /*
        由大到小
        a.length = 5
        5   4   3   2   1
        由小到大
        0   1   2   3   4
        1   2   4   5   6

        排名k = length - 由小到大排序后的index
        由小到大排序后的index = length - k

     */

    public int findKthLargest(int[] a, int k){
        return QuickSelect.quick(a, 0, a.length -1, a.length - k);
    }

    public static void main(String[] args) {
        LeetCode0215KthLargestElementInAnArray code = new LeetCode0215KthLargestElementInAnArray();
        // 应为5
        System.out.println(code.findKthLargest(new int[]{2, 1, 4, 5, 6}, 2));
    }
}
