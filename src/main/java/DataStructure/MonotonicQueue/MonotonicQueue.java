package DataStructure.MonotonicQueue;

import java.util.LinkedList;

/**
 * 单调递减队列
 * - 一种数据结构，队列中的元素按照从大到小的顺序排列
 * - 常用来解决滑动窗口问题
 */
public class MonotonicQueue {
    private LinkedList<Integer> deque = new LinkedList<>();  //双端队列

    /**
     * 获取队列头的元素
     * @return 队列头的元素
     */
    public Integer peek(){
        return deque.peekFirst();
    }

    /**
     * 获取队列头的元素并移除
     */
    public Integer poll(){
        return deque.pollFirst();
    }

    /**
     * 将元素加入队列尾部，如果队列的尾部有小于新元素的元素，现将这些元素移除，以保证队列中的元素按照从大到小的顺序排列
     * @param t 待加入的元素
     */
    public void offer(Integer t){
        while(!deque.isEmpty() && deque.peekLast() < t){
            deque.pollLast();
        }

        deque.offerLast(t);
    }

    @Override
    public String toString() {
        return deque.toString();
    }

    public static void main(String[] args) {
        MonotonicQueue q = new MonotonicQueue();
        for(int i : new int[]{1,3,-1,-3,5,3,6,7}){
            q.offer(i);
            System.out.println(q);
        }
    }



}
