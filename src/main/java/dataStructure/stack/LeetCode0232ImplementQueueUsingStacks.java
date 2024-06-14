package dataStructure.stack;

/*
     双栈模拟队列
     队列头            队列尾
     a b                 a b
     顶   底          底   顶
     s1                   s2

     队列尾添加
     s2.push(a)
     s2.push(b)

     队列头删除

     先将s2的所有元素移动到s1，使其位置反转
     s1.pop()
     s1.pop()
 */
public class LeetCode0232ImplementQueueUsingStacks<E> {

    ArrayStack<Integer> s1 = new ArrayStack<>(100);
    ArrayStack<Integer> s2 = new ArrayStack<>(100);

    public LeetCode0232ImplementQueueUsingStacks() {
    }

    public void push(int x) {
        s2.push(x);
    }

    public int pop() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.pop();
    }

    public int peek() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        return s1.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

}

