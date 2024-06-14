package DataStructure.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListSentinelTest {

    private SinglyLinkedListSentinel getLinkedList(){
        SinglyLinkedListSentinel list = new SinglyLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        return list;
    }

    @Test
    @DisplayName("测试loop1")
    public void test1(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop1(value->
                System.out.println(value));
    }

    @Test
    @DisplayName("测试loop2")
    public void test2(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop2(value->
                System.out.println(value));
    }

    @Test
    @DisplayName("测试iterator")
    public void test3(){
        SinglyLinkedListSentinel list = getLinkedList();

        for(Integer value:list){
            System.out.println(value);
        }
    }

    @Test
    @DisplayName("测试addLast")
    public void test4(){
        SinglyLinkedListSentinel list = getLinkedList();

        Assertions.assertIterableEquals(List.of(1,2,3,4), list);
    }

    @Test
    @DisplayName("测试get")
    public void test5(){
        SinglyLinkedListSentinel list = getLinkedList();

        assertEquals(3,list.get(2));
        assertThrows(IllegalArgumentException.class,()->list.get(10));

    }

    @Test
    @DisplayName("测试insert和addFirst")
    public void test6(){
        SinglyLinkedListSentinel list = getLinkedList();
        list.insert(0,5);
        assertIterableEquals(List.of(5,1,2,3,4),list);

        list = getLinkedList();
        list.insert(2,5);
        assertIterableEquals(List.of(1,2,5,3,4),list);

        list = getLinkedList();
        list.insert(4,5);
        assertIterableEquals(List.of(1,2,3,4,5),list);

        assertThrows(IllegalArgumentException.class,
                () -> getLinkedList().insert(5,5));

        list = getLinkedList();     //链表非空
        list.addFirst(5);
        assertIterableEquals(List.of(5,1,2,3,4),list);

        SinglyLinkedListSentinel list1 = new SinglyLinkedListSentinel();    //链表为空
        list1.addFirst(1);
        assertIterableEquals(List.of(1),list1);
    }

    @Test
    @DisplayName("测试removeFirst")
    public void test7(){
        SinglyLinkedListSentinel list = getLinkedList();

        list.removeFirst();
        assertIterableEquals(List.of(2,3,4),list);

        list.removeFirst();
        assertIterableEquals(List.of(3,4),list);

        list.removeFirst();
        assertIterableEquals(List.of(4),list);

        list.removeFirst();
        assertIterableEquals(List.of(),list);

        assertThrows(IllegalArgumentException.class,()->list.removeFirst());  //删除空链表，报异常

    }

    @Test
    @DisplayName("测试remove")
    public void test8(){
        SinglyLinkedListSentinel list1 = getLinkedList();
        list1.remove(2);
        assertIterableEquals(List.of(1,2,4),list1);

        SinglyLinkedListSentinel list2 = getLinkedList();
        list2.remove(0);
        assertIterableEquals(List.of(2,3,4),list2);

        SinglyLinkedListSentinel list3 = getLinkedList();
        assertThrows(IllegalArgumentException.class,()->list3.remove(10));   //上一个节点为null

        SinglyLinkedListSentinel list4 = getLinkedList();
        assertThrows(IllegalArgumentException.class,()->list4.remove(4));   //被删除的节点为null（上一个节点并不为null）

    }

}