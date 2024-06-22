package DataStructure.LinkedList;

import DataStructure.Heap.LinkedList.DoublyLinkedListSentinel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListSentinelTest {

    private DoublyLinkedListSentinel getLinkedList() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        return list;
    }


    @Test
    @DisplayName("测试insert和addFirst")
    public void test1() {
        DoublyLinkedListSentinel list1 = getLinkedList();
        list1.insert(2, 5);
        assertIterableEquals(List.of(1, 2, 5, 3, 4), list1);

        DoublyLinkedListSentinel list2 = getLinkedList();
        list2.insert(4, 5);
        assertIterableEquals(List.of(1, 2, 3, 4, 5), list2);

        DoublyLinkedListSentinel list3 = getLinkedList();


        DoublyLinkedListSentinel list4 = getLinkedList();
        list4.addFirst(5);
        assertIterableEquals(List.of(5, 1, 2, 3, 4), list4);

    }

    @Test
    @DisplayName("测试remove和removeFirst")
    public void test2() {
        DoublyLinkedListSentinel list1 = getLinkedList();
        list1.remove(2);
        assertIterableEquals(List.of(1, 2, 4), list1);

        DoublyLinkedListSentinel list2 = getLinkedList();
        assertThrows(IllegalArgumentException.class, ()
                -> list2.remove(5));

        DoublyLinkedListSentinel list3 = getLinkedList();
        assertThrows(IllegalArgumentException.class, ()
                -> list3.remove(4));

        DoublyLinkedListSentinel list4 = getLinkedList();
        list4.removeFirst();
        assertIterableEquals(List.of(2, 3, 4), list4);
    }

    @Test
    @DisplayName("测试removeLast")
    public void test3() {
        DoublyLinkedListSentinel list1 = getLinkedList();
        list1.removeLast();
        assertIterableEquals(List.of(1, 2, 3), list1);

        DoublyLinkedListSentinel list2 = new DoublyLinkedListSentinel();
        assertThrows(IllegalArgumentException.class, ()
                -> list2.removeLast());
    }

    @Test
    @DisplayName("测试addLast")
    public void test4() {
        DoublyLinkedListSentinel list1 = getLinkedList();
        list1.addLast(5);
        assertIterableEquals(List.of(1, 2, 3, 4, 5), list1);

        DoublyLinkedListSentinel list2 = new DoublyLinkedListSentinel();
        list2.addLast(5);
        assertIterableEquals(List.of(5), list2);
    }
}