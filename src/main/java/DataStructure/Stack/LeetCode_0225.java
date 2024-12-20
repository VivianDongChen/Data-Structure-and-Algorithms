package DataStructure.Stack;

import DataStructure.Queue.ArrayQueue_III;

/**
 * 单队列模拟栈
 *
 * 栈顶        栈底
 *  c   b   a
 * 队列头      队列尾
 *
 * queue.offer(a)
 * queue.offer(b)
 * queue.offer(c)
 *
 * push 添加
 *        - 将新加入元素前面所有的元素从队列头移动到队列尾
 * pop 移除
 *        - 直接移除队列头元素
 */
public class LeetCode_0225 {

    ArrayQueue_III<Integer> queue = new ArrayQueue_III<>(100);
    private int size = 0;

    public LeetCode_0225() {

    }

    public void push(int x) {
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
        size++;
    }

    public int pop() {
        size--;
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();

    }
}
