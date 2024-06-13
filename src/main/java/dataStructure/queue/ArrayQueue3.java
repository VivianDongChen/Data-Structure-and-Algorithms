package dataStructure.queue;

import java.util.Iterator;

/**
 * 使用环形数组来实现队列 - 方法3
 * 方法3: 只把head和tail看成不断累加的整数，需要用它们来定位数值的时候，再进行计算，这样head和tail就不存在重合的问题了
 */
public class ArrayQueue3<E> implements Queue<E>,Iterable<E>{

    private E[] array;
    private int head = 0;
    private int tail = 0;


    @SuppressWarnings("all")
    public ArrayQueue3(int capacity) {
        array = (E[]) new Object[capacity];

    }
    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false;
        }
        array[tail % array.length] = value;
        tail ++;

        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        E value = array[head % array.length];
        head ++;
        return value;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return array[head % array.length];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head  == array.length;
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
                E value = array[p % array.length];
                p ++;
                return value;
            }
        };
    }
}
