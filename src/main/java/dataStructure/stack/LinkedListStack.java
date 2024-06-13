package dataStructure.stack;

import java.util.Iterator;

//用单项带哨兵链表来实现栈
public class LinkedListStack<E> implements Stack<E>,Iterable<E>{

    static class Node<E>{
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private int capacity;
    private int size;
    private Node<E> head = new Node<>(null,null); //头指针，指向哨兵元素

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 2
     * head -> 1 -> null
     *
     * head -> 2 -> 1 -> null
     */
    @Override
    public boolean push(E value) {
        if(isFull()){
            return false;
        }
        head.next = new Node<>(value,head.next);
        size++;
        return true;
    }
    /**
     * head -> 2 -> 1 -> null
     */
    @Override
    public E pop() {
        if(isEmpty()){
            return null;
        }
        Node<E> first = head.next;
        head.next = first.next;
        size--;
        return first.value;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }



}
