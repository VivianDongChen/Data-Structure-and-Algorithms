package com.itheima.algorithms.dataStructure.linkedList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircularDoublyLinkedListSentinelTest {

    private CircularDoublyLinkedListSentinel getList(){
        CircularDoublyLinkedListSentinel list = new CircularDoublyLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        return list;
    }

    @Test
    @DisplayName("测试addFirst")
    public void test1(){
        CircularDoublyLinkedListSentinel list = new CircularDoublyLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        assertIterableEquals(List.of(5,4,3,2,1),list);

    }


    @Test
    @DisplayName("测试addLast")
    public void test2(){
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        assertIterableEquals(List.of(1,2,3,4,5),list);

    }

    @Test
    @DisplayName("测试removeFirst")
    public void test3(){
        CircularDoublyLinkedListSentinel list = getList();

        list.removeFirst();
        assertIterableEquals(List.of(2,3,4,5),list);

        list.removeFirst();
        assertIterableEquals(List.of(3,4,5),list);

        list.removeFirst();
        assertIterableEquals(List.of(4,5),list);

        list.removeFirst();
        assertIterableEquals(List.of(5),list);

        list.removeFirst();
        assertIterableEquals(List.of(),list);

        assertThrows(IllegalArgumentException.class,
                list::removeFirst);

    }


    @Test
    @DisplayName("测试removeLast")
    public void test4(){
        CircularDoublyLinkedListSentinel list = getList();

        list.removeLast();
        assertIterableEquals(List.of(1,2,3,4),list);

        list.removeLast();
        assertIterableEquals(List.of(1,2,3),list);

        list.removeLast();
        assertIterableEquals(List.of(1,2),list);

        list.removeLast();
        assertIterableEquals(List.of(1),list);

        list.removeLast();
        assertIterableEquals(List.of(),list);

        assertThrows(IllegalArgumentException.class,
                list::removeLast);

    }

    @Test
    @DisplayName("测试removeByValue")
    public void test5(){
        CircularDoublyLinkedListSentinel list = getList();

        list.removeByValue(6);
        assertIterableEquals(List.of(1,2,3,4,5),list);

        list.removeByValue(3);
        assertIterableEquals(List.of(1,2,4,5),list);


    }

}