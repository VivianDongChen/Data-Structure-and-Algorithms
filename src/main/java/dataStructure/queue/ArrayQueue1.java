package dataStructure.queue;

import java.util.Iterator;

/**
 * 环形数组的优点：
 * - 对比普通数组，起点和终点更为自由，不用考虑数据移动
 * - “环”意味着不会存在“越界”问题
 * - 数组性能更加
 * - 环形数组比较适合实现有界队列，RingBuffer等
 *
 * @param <E>
 */
public class ArrayQueue1<E> implements Queue<E>, Iterable<E> {

    private E[] array;
    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("all")      //抑制所有编译器警告
    public ArrayQueue1(int capacity) {
        array = (E[]) new Object[capacity + 1];  //初始化泛型数组 array，数组长度为 capacity + 1(有一个不能存数据，用来存尾指针）
        //由于Java中不允许直接创建泛型数组，所以这里通过创建一个 Object 数组并进行类型转换来实现。
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;  //计算环形数组索引
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        head = (head + 1) % array.length;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % array.length == head;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                return value;
            }
        };
    }
}
