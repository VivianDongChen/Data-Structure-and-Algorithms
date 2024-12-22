package Algorithms.BinarySearch;

import java.util.Arrays;
/**
 * LeetCode 875 Koko Eating Bananas
 * 时间复杂度：
 * - 二分查找在 [1, max(piles)] 范围内进行，范围的长度为 max(piles)。每次迭代将搜索范围减半，因此需要 O(log(max(piles))) 次迭代。
 * - 计算总时间 sum 的复杂度：对于每个二分查找的速度 m，需要遍历整个 piles 数组，计算所需时间 sum。遍历数组的复杂度为 O(n)，其中 n 是数组的长度。
 *  综上，总时间复杂度为：O(n⋅log(max(piles)))
 */
public class LeetCode_875 {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = Arrays.stream(piles).max().getAsInt(); // 最大速度（最大堆的香蕉数）

        while (l < r) {
            int m = l + (r - l) / 2;
            int sum = 0;  //计算时间
            for(int pile : piles){
                sum += (pile + m - 1) / m; //向上取整， 当使用 pile / m + 1 时，对于 pile % m == 0（即 pile 刚好能被 m 整除）的情况，结果会比实际多 1。
            }

            if ( sum <= h){  //用时正好或者是用时较短，吃得太快了，减慢速度或者继续寻找更小速度的可能
                r = m;   //m是个可能的解
            }else if (sum > h){  //用时过长，吃得太慢，提高速度
                l = m + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        LeetCode_875 test = new LeetCode_875();
        System.out.println(test.minEatingSpeed(new int[]{30,11,23,4,20},6));  //expected： 23
    }
}
