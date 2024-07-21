package DataStructure.MonotonicQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 滑动窗口的最大值 - 使用单调递减队列来解
 */
public class LeetCode0239SlidingWindowMaximum {

    //每次向单调递减队列加入元素， 队列头元素即为滑动窗口最大值
    static int[] maxSlidingWindow(int[] nums, int k){
        MonotonicQueue queue = new MonotonicQueue();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            //保证单调队列中不包含窗口之外的元素
            if(i >= k && queue.peek() == nums[i-k]){
                queue.poll();
            }
            queue.offer(nums[i]);
            if(i >= k - 1){
                list.add(queue.peek());
            }
        }
        return list.stream(). mapToInt(Integer :: intValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow (new int[]{1, 3, -1, -3, -4, 5, 3, 6, 7}, 3))); //[3, 3, -1, 5, 5, 6, 7]
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{7, 2, 4}, 2))); // [7, 4]
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3))); // [3, 3, 2, 5]
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{-7, -8, 7, 5, 7, 1, 6, 0}, 4))); // [7, 7, 7, 7, 7]
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,-1}, 1)));
    }
}
