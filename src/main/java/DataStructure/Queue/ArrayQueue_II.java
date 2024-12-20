package DataStructure.Queue;

import java.util.Iterator;

/**
 * 使用环形数组来实现队列 - 方法2
 * 方法2: 用size和capacity来判断队列是空还是满， 空或者满，tail和head都会重合
 */
public class ArrayQueue_II<E> implements Queue<E>, Iterable<E>{

    private E[] array;
    private int head = 0;
    private int tail = 0;

    private int size = 0;


    @SuppressWarnings("all")
    public ArrayQueue_II(int capacity) {
        array = (E[]) new Object[capacity];

    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;  //计算环形数组索引
        size ++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        head = (head + 1) % array.length;
        size --;
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
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                count ++;
                return value;
            }
        };
    }
}