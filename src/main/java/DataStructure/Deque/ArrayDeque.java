package DataStructure.Deque;

import java.util.Iterator;

/**
 * 基于循环数组实现
 * 特点：tail停下来的位置不存储，会浪费一个位置
 * @param <E> 队列中元素类型
 */
public class ArrayDeque<E> implements Deque<E>,Iterable<E>{

    /*
      h - head
      t - tail

                  h
              t
      0   1   2   3
      a   b       c

      offerLast(a)  先添加元素 t++
      offerLast(b)
      offerFirst(a)  先h-- 再添加元素

      h == t 空
      h ～ t == 数组长度 -1 满

      pollFirst()  先获取需移除的值，h++
      pollLast()   先t--,再获取要移除的值

     */

    //避免索引越界的方法
    static int inc(int i, int length){
        if(i + 1 >= length){
            return 0;
        }
        return i + 1;
    }

    static int dec(int i ,int length){
        if (i - 1 < 0){
            return length - 1;
        }
        return i - 1;
    }

    E[] array;
    int head;
    int tail;

    @SuppressWarnings("all")
    public ArrayDeque(int capacity) {
        array = (E[])new Object[capacity+1];
    }

    @Override
    public boolean offerFirst(E e) {
        if(isFull()){
            return false;
        }
        head = dec(head, array.length);
        array[head] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if(isFull()){
            return false;
        }
        array[tail] = e;
        tail = inc(tail, array.length);
        return true;
    }

    @Override
    public E pollFirst() {
        if(isEmpty()){
            return null;
        }
        E e = array[head];
        array[head] = null; //help GC 释放内存，帮助垃圾回收
        head = inc(head, array.length);
        return e;
    }

    @Override
    public E pollLast() {
        if(isEmpty()){
            return null;
        }
        tail = dec(tail, array.length);
        E e = array[tail];
        array[tail] = null; //help GC 释放内存，帮助垃圾回收
        return e;
    }

    @Override
    public E peekFirst() {
        if(isEmpty()){
            return null;
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if(isEmpty()){
            return null;
        }
        return array[dec(tail, array.length)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    /*
      h
               t
      0  1  2  3
      a  b  c
      tail > head
      tail - head = array.length - 1 满

         h
      t
      0  1  2  3
         c  b  a
      tail < head
      head - tail = 1 满

     */
    @Override
    public boolean isFull() {
        if(head < tail){
            return tail - head == array.length -1;
        } else if (tail < head){
            return head - tail == 1;
        } else{
            return false;
        }
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
                E e = array[p];
                p = inc(p,array.length);
                return e;
            }
        };
    }
}
