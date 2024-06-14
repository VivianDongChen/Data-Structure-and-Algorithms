package DataStructure.PriorityQueue;

import DataStructure.Queue.Queue;

/**
 * 基于无序数组实现
 * @param <E> 队列中元素类型，必须实现Priority接口
 */
public class PriorityQueue1<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue1(int capacity) {
        array = new Priority[capacity];
    }

    /**
     * 直接放到数组最后即可，时间复杂度：O（1）
     * @param e - 待插入的值
     * @return
     */
    @Override
    public boolean offer(E e) {
        if(isFull()){
            return false;
        }
//        array[size] = e;
//        size++;
        array[size++] = e;
        return true;
    }

    /**
     * index         0   1   2   3   4   5   6
     * E(priority)   4   2   1   5   10  7   3
     *              max
     *                   i
     * 返回优先级最高的索引值
     * @return 优先级最高的索引值
     */
    private int selectMax(){
        int max = 0;
        for (int i = 1; i < size; i++) {
            if(array[i].priority() > array[max].priority()){
                max = i;
            }
        }
        return max;
    }

    /**
     * 必须先找到优先级最高的，时间复杂度：O（n）
     * @return
     */
    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        int max = selectMax();
        E e = (E) array[max];
        remove(max);
        return e;
    }

    /**
     * index         0   1   2   3   4   5   6
     * E(priority)   4   2   1   5   10  7   3
     *                              index
     */
    private void remove(int index){
        if(index < size -1){    //删除的不是数组的最后一位
            //移动
            System.arraycopy(array,index+1,array,index,size-1-index);
        }
        size--;    //如果删除的是最后一位，直接size-1即可
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        int max = selectMax();
        return (E) array[max];
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
