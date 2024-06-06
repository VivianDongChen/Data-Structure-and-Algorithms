package com.itheima.algorithms.dataStructure.linkedList;

public class DoublyLinkedListSentinel implements Iterable<Integer>{

    static class Node{
        Node prev;   //上一个节点
        int value;   //值
        Node next;  //下一个节点

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node head; //头哨兵
    private Node tail; //尾哨兵
    
    public DoublyLinkedListSentinel(){




    }

    public void addFirst(int value){

    }

    public void removeFirst(){

    }

    public void addLast(){

    }

    public void removeLast(){

    }


}
