package DataStructure.LFUCache;

import java.util.HashMap;

/**
 * 设计LFU缓存
 * 使用数据结构：
 *   - HashMap1（Java自带）
 *       key
 *       value 是节点（key, value)
 *       可以根据key很快找到节点
 *   - HashMap2(Java自带）
 *       freq
 *       value 是双向链表
 *   - 双向链表带哨兵（自己实现）
 *       里面存储节点（key，value）
 *       最新使用（加入或者更新）放在链表头，最后使用（介入或者更新）放在链表尾，并可以直接删除
 */
public class LeetCode_0460 {
    /**
     * 节点类
     */
    static class Node{
        Node prev;
        Node next;
        int key;
        int value;
        int freq = 1;   //频度, 初始值为1

        public Node(){

        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 双向链表类
     */
    static class DoublyLinkedList{
        Node head;  //头指针
        Node tail;  //尾指针
        int size;
        public DoublyLinkedList(){
            head = tail = new Node(); //哨兵节点
            head.next = tail;
            tail.prev = head;
        }

        //头部添加   O(1)
        public void addFirst(Node newFirst){
            Node oldFirst = head.next;
            newFirst.prev = head;
            newFirst.next = oldFirst;
            head.next = newFirst;
            oldFirst.prev = newFirst;
            size++;
        }

        //已知节点删除     0(1)
        public void remove(Node node){
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }

        //尾部删除    0(1)
        public Node removeLast(){
            Node last = tail.prev;
            remove(last);
            return last;
        }
        public boolean isEmpty(){
            return size == 0;
        }
    }
    private HashMap<Integer, Node> kvMap = new HashMap<>();
    private HashMap<Integer, DoublyLinkedList> freqMap = new HashMap<>();
    private int capacity;
    private int minFreq = 1;  //最小频度

    public LeetCode_0460(int capacity) {
        this.capacity = capacity;
    }

    /*
           key 不存在
               返回 -1
           key 存在
               返回 value值
               增加节点的使用频度，将其转移到频度+1的链表中


         */
    public int get(int key){
        if(!kvMap.containsKey(key)){
            return -1;
        }
        Node node = kvMap.get(key);
        DoublyLinkedList oldList = freqMap.get(node.freq);  //找到所在频度旧链表
        oldList.remove(node);
        //如果删除的是最小频度链表，并且删除之后链表为空，最小频度+1
        if(oldList.isEmpty() && node.freq == minFreq){
            minFreq++;
        }

        node.freq++;
        //获取这个频度对应的链表，如果这个频率之前不存在，要现将它创建出来， 放入freqMap
//        DoublyLinkedList newList = freqMap.get(node.freq);
//        if (newList == null){
//            newList = new DoublyLinkedList();
//            freqMap.put(node.freq, newList);
//        }
//        newList.addFirst(node);
        freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).addFirst(node);
        return node.value;
    }


    /*
       更新
           将节点value更新，增加节点的频度，将其转移到频度+1的链表当中
       新增
           检查是否超过容量，若超过，淘汰minFreq链表的最后节点
           创建新节点，放入kvMap，并加入频度为1的双向链表
     */
    public void put(int key, int value){
        if(kvMap.containsKey(key)){ //更新
            Node node = kvMap.get(key);
            DoublyLinkedList oldList = freqMap.get(node.freq);
            oldList.remove(node);
            if(oldList. isEmpty() && node.freq == minFreq){
                minFreq++;
            }
            node.freq++;
            freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).addFirst(node);
            node.value = value;
        }else{    //新增
            if(kvMap.size() == capacity){
                Node node = freqMap.get(minFreq).removeLast();
                kvMap.remove(node.key);
            }
            Node node = new Node(key, value);
            kvMap.put(key,node);
            freqMap.computeIfAbsent(1, k -> new DoublyLinkedList()).addFirst(node);
            minFreq = 1;
        }
    }

    public static void main(String[] args) {
       LeetCode_0460 cache = new LeetCode_0460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1 freq=2
        cache.put(3, 3);
        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 3 freq=2
        cache.put(4, 4);
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3  freq=3
        System.out.println(cache.get(4)); // 4  freq=2

    }

}
