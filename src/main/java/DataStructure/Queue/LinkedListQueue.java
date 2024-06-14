package DataStructure.Queue;

import java.util.Iterator;

//以单向环形带哨兵的链表来实现队列
public class LinkedListQueue <E> implements Queue<E>,Iterable<E>{

    /**
     * 定义出节点类
     */
    private static class Node<E>{
        E value;
        Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    //定义出LinkedListQueue的属性
    private Node<E> head = new Node<>(null,null);   //头指针指向哨兵（null）
    private Node<E> tail = head;     //尾指针和头指针重合
    private int size;        //节点数
    private int capacity = Integer.MAX_VALUE;    //容量


    //定义出LinkedListQueue的constructor

    {
        tail.next = head;      // 尾指针指向头指针     所有构造方法共用的语句放在这里
    }


    public LinkedListQueue(int capacity) {
        this.capacity = capacity;
    }

    public LinkedListQueue(){}


    /**
     * 新节点指向head，原来的尾指向新节点，新节点成为新的尾
     * @param value - 待插入的值
     * @return
     */
    @Override
    public boolean offer(E value) {
        if(isFull()){
            return false;
        }
        Node<E> added = new Node<>(value,head);   //新节点指向原来的头节点
        tail.next = added;   //原来尾节点指向新节点
        tail = added;  //新节点成为新的尾巴
        size ++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        Node<E> first = head.next;
        head.next = first.next;
        if(first == tail){
            tail = head;
        }
        size --;
        return first.value;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;   //队列为空，返回null
        }
        return (E) head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;  //头指针和尾指针重合
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;  //从哨兵的下一个开始迭代
            @Override
            public boolean hasNext() {
                return p != head;  // p是head时，循环完毕
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
