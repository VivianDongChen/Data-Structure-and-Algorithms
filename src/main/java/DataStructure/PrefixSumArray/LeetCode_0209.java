package DataStructure.PrefixSumArray;

import java.util.Arrays;

/**
 * 时间复杂度 O（n log n), 空间复杂度 O (n)
 */
public class LeetCode_0209 {

    /*
    使用的知识点一（数据结构）：

    A prefix sum array is a data structure that allows for efficient computation of the sum of elements in a subarray
     (a contiguous segment of an array).The prefix sum array is built from an original array and provides a way to
     quickly calculate the sum of elements between any two indices.
     P[i]=0+A[0]+A[1]+A[2]+...+A[i-1]

     Initialize P[0] to 0.
     For each subsequent index i, compute:P[i]=P[i−1]+A[i-1]

     Once the prefix sum array is built, you can quickly compute the sum of any subarray A[l] to A[r] using:
     Sum(l,r)=P[r+1]−P[l]
     (If l is 0, then the sum is simply P[r+1].)

     A = [3, 1, 4, 1, 5]

     The prefix sum array would be constructed as follows:

        P[0] = 0 （额外的前缀 0）
        P[1] = P[0] + A[0] = 0 + 3 = 3
        P[2] = P[1] + A[1] = 3 + 1 = 4
        P[3] = P[2] + A[2] = 4 + 4 = 8
        P[4] = P[3] + A[3] = 8 + 1 = 9
        P[5] = P[4] + A[4] = 9 + 5 = 14

    prefix sum array是排好序的

    使用的知识点二：
    Arrays.binarySearch()

    int index = Arrays.binarySearch(array, key);
    array: 要搜索的已排序数组。
    key: 要查找的元素。
    返回值:
    如果找到该元素，返回该元素在数组中的索引（位置）。
    如果没有找到该元素，返回一个负值，表示元素未找到。该负值的具体值为 -(插入点) - 1，其中插入点是该元素可以被插入以保持数组的有序性的位置。

     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];

        //build prefix sums array （前缀和数组）， 时间复杂度O(n), 空间复杂度 O(n)
        for(int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int min = Integer.MAX_VALUE;
        // 时间复杂度 O（n log n)
        for(int i = 0; i < n; i++) {
            //想要找到符合 prefixSum[j]-prefixSum[i] >= target 的j的位置
            int requiredSum = prefixSum[i] + target;
            //使用 binarySearch 查找符合条件的位置 j, 时间复杂度 O(log n)
            int j = Arrays.binarySearch(prefixSum, requiredSum);

            // 如果没有找到确切匹配值，返回负数，计算插入点
            if(j < 0){
                j = -(j+1);
            }
            //检查j是否在有效范围内
            if(j<=n){
                min = Math.min(min, j - i);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
