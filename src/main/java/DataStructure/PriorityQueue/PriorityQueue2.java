package DataStructure.PriorityQueue;

import DataStructure.Queue.Queue;

/**
 * 基于有序数组的实现（入队复杂，出队简单）
 *
 *      index         0   1   2   3   4
 *      E(priority)   1   4   5   8  10
 *
 * @param <E> 队列中的元素类型，必须实现Priority接口
 */
public class PriorityQueue2<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue2(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E e) {
        if(isFull()){
            return false;
        }
        insert(e);
        size++;
        return true;
    }

    /**
     *                               i
     * index         0   1   2   3   4
     * E(priority)   1   4   5   8  10
     *
     * 时间复杂度：O(n)
     */
    private void insert(E e){
        int i = size - 1;
        while(i >= 0 && array[i].priority() > e.priority()){
            array[i+1] = array[i];
            i--;
        }
        array[i+1] = e;
    }

    /**
     * 时间复杂度：O(1)
     */
    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        E e = (E) array[size-1];
//        size--;
//        array[size] = null; //help GC
        array[--size] = null; //help GC
        return e;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return (E) array[size-1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
