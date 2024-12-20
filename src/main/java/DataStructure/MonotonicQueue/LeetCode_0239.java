package DataStructure.MonotonicQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 滑动窗口的最大值 - 使用单调递减队列来解
 */
public class LeetCode_0239 {

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
            //在窗口大小满足条件要求（= k),开始从队列头取出数据
            if(i >= k - 1){
                list.add(queue.peek());
            }
        }
        //将一个int的list转化为一个int数组的方法：
        //list.stream() 将 List<Integer> 转换为 Stream<Integer>。
        //mapToInt(Integer::intValue) 将 Stream<Integer> 中的 Integer 对象映射为 int，生成一个 IntStream。
        //toArray() 将 IntStream 中的元素收集到一个 int 数组中。
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
