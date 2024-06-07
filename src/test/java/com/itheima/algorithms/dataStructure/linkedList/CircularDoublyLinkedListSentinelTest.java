package com.itheima.algorithms.dataStructure.linkedList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircularDoublyLinkedListSentinelTest {

    @Test
    @DisplayName("测试addFirst")
    public void test1(){
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
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

}