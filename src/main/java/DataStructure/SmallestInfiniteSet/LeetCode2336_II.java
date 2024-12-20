package DataStructure.SmallestInfiniteSet;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 使用 最小堆 (PriorityQueue) 来获取最小值，HashSet 用来去重，并使用 cur 动态生成无限正整数集合的最小值
 * 空间复杂度：堆 (PriorityQueue) 和辅助集合 (HashSet) 的总空间复杂度为O(k)，其中 k 是显式存储的数字数量。
 */
public class LeetCode2336_II {
    private int cur;
    private PriorityQueue<Integer> heap;
    private HashSet<Integer> set;

    public LeetCode2336_II() {
        cur = 1;
        heap = new PriorityQueue<>();
        set = new HashSet<>();
    }

    /**
     * 时间复杂度：如果堆为空，操作仅涉及 cur，为O(1)；否则弹出堆顶元素，为O(logk)。其中k是堆中的元素数量。
     */
    public int popSmallest(){
        if(!heap.isEmpty()){
            int smallest = heap.poll();
            set.remove(smallest);
            return smallest;
        }else{
            return cur++;
        }
    }

    /**
     * 插入堆操作的复杂度为O(logk)。去重检查使用 HashSet，时间复杂度为O(1)。总体复杂度： O(logk)。
     */
    public void addBack(int num){
        if(cur > num && !set.contains(num)){
            set.add(num);
            heap.offer(num);
        }
    }

    public static void main(String[] args) {
        LeetCode2336_II test = new LeetCode2336_II();
        test.addBack(2);
        System.out.println(test.popSmallest());
        System.out.println(test.popSmallest());
        System.out.println(test.popSmallest());
        test.addBack(1);
        System.out.println(test.popSmallest());
        System.out.println(test.popSmallest());
        System.out.println(test.popSmallest());
    }




}
