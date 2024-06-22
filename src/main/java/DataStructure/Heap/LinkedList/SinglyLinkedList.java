package DataStructure.LinkedList;


import java.util.Iterator;
import java.util.function.Consumer;

//单向链表
public class SinglyLinkedList implements Iterable<Integer>{    //整体
    private Node head = null; //头指针

    /**
     * 节点类
     */
    static class Node{
        int value; //值
        Node next;  //下一个节点指针

        public Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 添加：向链表头部添加
     * @param value -待添加值
     */
    public void addFirst(int value){
        head = new Node(value, head);
    }

    /**
     * 遍历方式1
     * @param consumer - 遍历要执行的操作，入参：每个元素
     */
    public void loop1(Consumer<Integer> consumer){
        Node p = head;
        while(p != null){
            consumer.accept(p.value);
            p = p.next;
        }
    }

    /**
     * 遍历方式2 - 用for循环代替while循环
     * @param consumer - 遍历要执行的操作，入参：每个元素
     */
    public void loop2(Consumer<Integer> consumer){
        for(Node p = head; p != null; p = p.next){
            consumer.accept(p.value);
        }
    }

    /**
     * 遍历方式3 - 递归遍历
     */
    public void loop3(Consumer<Integer> before,Consumer<Integer> after){

        recursion(head,before,after);

    }

    private void recursion(Node curr,
                           Consumer<Integer> before,
                           Consumer<Integer> after){   //针对某个节点要进行的操作

        if(curr == null){
            return;
        }

        before.accept(curr.value);

        recursion(curr.next, before, after);

        after.accept(curr.value);

    }

    /**
     * 遍历方式4 - 迭代器循环
     * @return - 迭代器
     */
    @Override
    public Iterator<Integer> iterator() {
        // 匿名内部类 -> 带名字的内部类
        return new NodeIterator();
    }

    /**
     * helper方法：iterator的内部类
     */
    private class NodeIterator implements Iterator<Integer> {
        Node p = head;

        @Override
        public boolean hasNext() {   //是否有下一个元素
            return p != null;
        }

        @Override
        public Integer next() {   //返回当前值，并指向下一个元素
            int v = p.value;
            p = p.next;
            return v;
        }
    }

    /**
     * 查找：找到最后一个Node（如果是空链表，返回null）
     * @return 最后一个Node
     */
    private Node findLast(){

        if (head == null) {   //空链表
            return null;
        }

        Node p;           //非空链表
        for(p = head; p.next != null; p = p.next){
        }
        return p;
    }

    /**
     * 添加：向链表尾部添加
     * @param value - 待添加的值
     */
    public void addLast(int value){
        Node last = findLast();
        if(last == null){      //空链表
            addFirst(value);
            return;
        }
        last.next = new Node(value,null);    //非空链表
    }

    /**
     * helper方法：查找节点
     * @param index
     * @return
     */
    private Node findNode(int index){
        int i = 0;
        for(Node p = head; p != null; p = p.next,i++){
            if(i == index){
                return p;
            }
        }
        return null;  //没有找到
    }

    /**
     * 查找：找到索引对应的值
     * @param index - 索引
     * @return 值
     */
    public int get(int index) {
        Node node = findNode(index);
        if(node == null){
            throw illegalIndex(index);
        }
        return node.value;
    }

    /**
     * Helper方法：生成不合法索引异常
     * @param index
     * @return
     */
    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }

    /**
     * 添加：向索引位置插入
     */
    public void insert(int index, int value){
        if(index == 0){   //index等于0的情况
            addFirst(value);
            return;
        }

        Node prev = findNode(index-1); //找到上一个节点

        if (prev == null) { //找不到
            throw illegalIndex(index);
        }

        prev.next = new Node(value,prev.next);
    }

    /**
     * 删除：删除第一个节点
     */
    public void removeFirst(){
        if(head == null){
            throw illegalIndex(0);
        }
        head = head.next;

    }

    /**
     * 删除：按照索引删除节点
     */
    public void remove(int index){
        if(index == 0){
            removeFirst();
            return;
        }

        Node prev = findNode(index-1); //上一个节点
        if (prev == null){
            throw illegalIndex(index);
        }

        Node removed = prev.next; //被删除的节点
        if(removed == null){
            throw illegalIndex(index);
        }

        prev.next = removed.next;
    }
}

