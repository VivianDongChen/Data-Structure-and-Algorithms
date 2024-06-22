package DataStructure.LinkedList;

import DataStructure.Heap.LinkedList.SinglyLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class SinglyLinkedListTest {

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
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        for(Integer value:list){
            System.out.println(value);
        }
    }

    @Test
    @DisplayName("测试addLast")
    public void test4(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        Assertions.assertIterableEquals(List.of(1,2,3,4), list);
    }


    @Test
    @DisplayName("测试get")
    public void test5(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        int i = list.get(10);
        System.out.println(i);
    }

    @Test
    @DisplayName("测试insert")
    public void test6(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);  //index:2
        list.addLast(4);  //index:3

        list.insert(0,7);
        for (Integer value:list) {
            System.out.println(value);
        }
    }

    @Test
    @DisplayName("测试removeFirst")
    public void test7(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);  //index:2
        list.addLast(4);  //index:3

        list.removeFirst();
        for (Integer value:list) {
            System.out.println(value);
        }

        System.out.println("================");

        list.removeFirst();
        for (Integer value:list) {
            System.out.println(value);
        }

        System.out.println("================");

        list.removeFirst();
        for (Integer value:list) {
            System.out.println(value);
        }

        System.out.println("================");

        list.removeFirst();
        for (Integer value:list) {
            System.out.println(value);
        }

        list.removeFirst();  //报异常

    }

    @Test
    @DisplayName("测试remove")
    public void test8(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);  //index:0
        list.addLast(2);  //index:1
        list.addLast(3);  //index:2
        list.addLast(4);  //index:3

        list.remove(2);
        for (Integer value:list) {
            System.out.println(value);
        }

        System.out.println("================");

        list.remove(0);
        for (Integer value:list) {
            System.out.println(value);
        }

        System.out.println("================");
        list.remove(2);
        for (Integer value:list) {
            System.out.println(value);
        }

    }


    @Test
    @DisplayName("测试loop3")
    public void test9(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.loop3(value ->{
            System.out.println("before:"+value);
        },value ->{
            System.out.println("after:"+value);
        });
    }

}