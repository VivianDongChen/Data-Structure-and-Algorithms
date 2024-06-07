package dataStructure.linkedList;


import java.util.Iterator;
import java.util.function.Consumer;

//单向链表带哨兵
public class SinglyLinkedListSentinel implements Iterable<Integer> {    //整体
    private Node head = new Node(666, null); //头指针

    /**
     * 节点类
     */
    static class Node {
        int value; //值
        Node next;  //下一个节点指针

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 添加：向链表头部添加（就是insert的特殊情况）
     *
     * @param value -待添加值
     */
    public void addFirst(int value) {
        insert(0,value);
    }

    /**
     * 遍历方式1
     *
     * @param consumer - 遍历要执行的操作，入参：每个元素
     */
    public void loop1(Consumer<Integer> consumer) {
        Node p = head.next;
        while (p != null) {
            consumer.accept(p.value);
            p = p.next;
        }
    }

    /**
     * 遍历方式2 - 用for循环代替while循环
     *
     * @param consumer - 遍历要执行的操作，入参：每个元素
     */
    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head.next; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    /**
     * 遍历方式3 - 迭代器循环
     *
     * @return - 迭代器
     */
    @Override
    public Iterator<Integer> iterator() {
        // 匿名内部类 -> 带名字的内部类
        return new NodeIterator();
    }

    /**
     * helper方法：迭代器内部类
     */
    private class NodeIterator implements Iterator<Integer> {
        Node p = head.next;          //从哨兵后面的节点开始遍历

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
     * Help方法：找到最后一个Node
     *
     * @return 最后一个Node
     */
    private Node findLast() {
        Node p;
        for (p = head; p.next != null; p = p.next) {
        }
        return p;
    }

    /**
     * 添加：向链表尾部添加
     *
     * @param value - 待添加的值
     */
    public void addLast(int value) {
        Node last = findLast();
        last.next = new Node(value, null);
    }

    /**
     * Helper方法：找到索引对应的节点
     * @param index
     * @return
     */
    private Node findNode(int index) {
        int i = -1;                         //让哨兵对应的index是-1
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {         //如果找的是-1，就返回head，也就是哨兵
                return p;
            }
        }
        return null;  //没有找到
    }

    /**
     * 查找：找到索引对应的值
     *
     * @param index - 索引
     * @return 值
     */
    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {            //输入的索引不合法
            throw illegalIndex(index);
        }
        return node.value;
    }

    /**
     * Helper方法：生成不合法索引异常
     *
     * @param index
     * @return
     */
    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }                     

    /**
     * 添加：向索引位置插入
     * @param index 索引
     * @param value 待插入的值
     */
    public void insert(int index, int value) {
        Node prev = findNode(index - 1); //找到上一个节点

        if (prev == null) { //找不到
            throw illegalIndex(index);
        }

        prev.next = new Node(value, prev.next);
    }

    /**
     * 删除：删除第一个节点（就是remove的特殊情况）
     */
    public void removeFirst() {
        remove(0);
    }

    /**
     * 删除：按照索引删除节点
     */
    public void remove(int index) {
        Node prev = findNode(index - 1); //上一个节点
        if (prev == null) {
            throw illegalIndex(index);
        }

        Node removed = prev.next; //被删除的节点
        if (removed == null) {
            throw illegalIndex(index);
        }

        prev.next = removed.next;
    }
}

