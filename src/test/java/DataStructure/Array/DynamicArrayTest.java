package DataStructure.Array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    @DisplayName("测试添加")
    public void test1(){
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
//        dynamicArray.addLast(5);

        dynamicArray.add(2,5);

        for (int i = 0; i < 5; i++) {
            System.out.println(dynamicArray.get(i));

        }

    }

    @Test
    @DisplayName("测试遍历1")
    public void test2(){
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);

        dynamicArray.foreach((element)->{
            System.out.println(element);
        });

    }

    @Test
    @DisplayName("测试遍历2")
    public void test3(){
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);

        for(Integer element: dynamicArray){  //hasNext() next()
            System.out.println(element);
        }

    }

    @Test
    @DisplayName("测试遍历3")
    public void test4(){
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);

        dynamicArray.stream().forEach(element ->{
            System.out.println(element);
                }
        );
    }

    @Test
    @DisplayName("测试删除")
    public void test5(){
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);

        int removed = dynamicArray.remove(3);
        assertEquals(4,removed);

//        dynamicArray.stream().forEach(element ->
//                System.out.println(element));
        assertIterableEquals(List.of(1,2,3), dynamicArray);    //要求实现Iterable接口
    }

    @Test
    @DisplayName("测试扩容")
    public void test6(){
        DynamicArray dynamicArray = new DynamicArray();
        for (int i = 0; i < 9; i++) {
            dynamicArray.addLast(i+1);
        }

        assertIterableEquals(
                List.of(1,2,3,4,5,6,7,8,9),dynamicArray);
    }
}