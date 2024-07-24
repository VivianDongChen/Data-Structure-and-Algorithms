package LeetCode;

import java.util.HashMap;

/**
 * LRU缓存
 * 使用数据结构：
 *   - 双向链表带哨兵（自己实现）
 *   - HashMap（Java自带）
 */
public class LeetCode0146LRUCache {
    /**
     * 节点类
     */
    static class Node{
        Node prev;
        Node next;
        int key;
        int value;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 双向链表类
     */
    static class DoublyLinbkedlist{
        Node head;  //头指针
        Node tail;  //尾指针
        public DoublyLinbkedlist(){
            head = tail = new Node(); //哨兵节点
            head.next = tail;
            tail.prev = head;
        }

        //头部添加  head <-> 1 <-> 2 <-> tail  0(1)
        public void addFirst(Node newFirst){
            Node oldFirst = head.next;
            newFirst.prev = head;
            newFirst.next = oldFirst;
            head.next = newFirst;
            oldFirst.prev = newFirst;

        }

        //已知节点删除     0(1)
        public void remove(Node node){
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;

        }

        //尾部删除    0(1)
        public Node removeLast(){
            Node last = tail.prev;
            remove(last);
            return last;
        }
    }

    /**
     * 双向链表类
     */
    static class DoublyLinkedList{
        Node head;  //头指针
        Node tail;  //尾指针
        public DoublyLinkedList(){
            head = tail = new Node(); //哨兵节点
            head.next = tail;
            tail.prev = head;
        }

        //头部添加  head <-> 1 <-> 2 <-> tail  0(1)
        public void addFirst(Node newFirst){
            Node oldFirst = head.next;
            newFirst.prev = head;
            newFirst.next = oldFirst;
            head.next = newFirst;
            oldFirst.prev = newFirst;

        }

        //已知节点删除     0(1)
        public void remove(Node node){
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;

        }

        //尾部删除    0(1)
        public Node removeLast(){
            Node last = tail.prev;
            remove(last);
            return last;
        }
    }

    private final HashMap<Integer, Node> map = new HashMap<>();
    private final DoublyLinbkedlist list = new DoublyLinbkedlist();
    private int capacity;

    public LeetCode0146LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key){    //O（1）
        if(!map.containsKey(key)){
            return -1;
        }
        //找到了节点，将节点从原列表中删除，然后加入链表头
        Node node = map.get(key);
        list.remove(node);
        list.addFirst(node);

        return node.value;
    }

    public void put(int key, int value){
        if(map.containsKey(key)){  //更新 O(1)
            Node node = map.get(key);
            node.value = value;
        }else{     //新增
            Node node = new Node(key, value);
            list.addFirst(node);    // O(1)
            map.put(key, node);     // O(1)
            if(map.size() > capacity){
                Node removed = list.removeLast();
                map.remove(removed.key);
            }
        }

    }

    public static void main(String[] args) {
        LeetCode0146LRUCache cache = new LeetCode0146LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1
        cache.put(3, 3);
        System.out.println(cache.get(2)); // -1
        cache.put(4, 4);
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
    }
}
