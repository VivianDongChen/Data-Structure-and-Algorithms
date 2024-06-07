package com.itheima.algorithms.recursion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecursionBubbleSortTest {

    @Test
    @DisplayName("测试递归冒泡排序")
    public void test(){
        int[] expected = {1,2,3,4,5,6};

        int[] a1 = {2,4,1,3,5,6};
        RecursionBubbleSort.sort(a1);
        assertArrayEquals(expected,a1);

        int[] a2 = {6,5,1,3,4,2};
        RecursionBubbleSort.sort(a2);
        assertArrayEquals(expected,a2);
    }


}