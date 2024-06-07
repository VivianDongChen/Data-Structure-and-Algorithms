package dataStructure.linkedList;

import java.util.Iterator;

//双向环形链表带哨兵
public class CircularDoublyLinkedListSentinel implements Iterable<Integer>{


    private static class Node{
        Node prev;
        int value;
        Node next;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node sentinel = new Node(null,-1,null);

    public CircularDoublyLinkedListSentinel() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**
     * 遍历方式 - 迭代器遍历
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = sentinel.next; //初始值（哨兵不用遍历，从哨兵的下一个开始遍历）

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    /**
     * 添加到第一个
     * @param value 待添加值
     */
    public void addFirst(int value){
        Node a = sentinel;
        Node b = sentinel.next;
        Node added = new Node(a,value,b);
        a.next = added;
        b.prev = added;

    }

    /**
     * 添加到最后一个
     * @param value 待添加值
     */
    public void addLast(int value){
        Node a = sentinel.prev;
        Node b = sentinel;
        Node added = new Node(a,value,b);
        a.next = added;
        b.prev = added;
    }

    /**
     * 删除第一个
     */
    public void removeFirst(){
        Node removed = sentinel.next;
        if(removed == sentinel){
            throw illegalIndex(0);
        }
        Node a = sentinel;
        Node b = removed.next;

        a.next = b;
        b.prev = a;

    }

    /**
     * helper方法 - 生成异常
     * @param index
     * @return
     */
    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }

    /**
     * 删除最后一个
     */
    public void removeLast(){
        Node removed = sentinel.prev;

        if(removed == sentinel){
            throw illegalIndex(0);
        }

        Node a = removed.prev;
        Node b = sentinel;

        b.prev = a;
        a.next = b;

    }

    /**
     * 根据值删除
     * @param value 目标值
     */
    public void removeByValue(int value){
        Node removed = findByValue(value);
        if(removed == null){
            return;  //不用删
        }
        Node a = removed.prev;
        Node b = removed.next;
        a.next = b;
        b.prev = a;

    }


    /**
     * helper方法：根据值找到节点
     * @param value 目标值
     * @return
     */
    private Node findByValue(int value){
        Node p = sentinel.next;
        while(p != sentinel){
            if(p.value == value){
                return p;
            }
            p = p.next;
        }
        return null;
    }
}
