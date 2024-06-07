package com.itheima.algorithms.recursion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecursionBubbleSortTest {

    @Test
    @DisplayName("测试递归冒泡排序基础版")
    public void test1(){
        int[] expected = {1,2,3,4,5,6};

        int[] a1 = {2,4,1,3,5,6};
        RecursionBubbleSort.sort(a1);
        assertArrayEquals(expected,a1);

        int[] a2 = {6,5,1,3,4,2};
        RecursionBubbleSort.sort(a2);
        assertArrayEquals(expected,a2);
    }

    @Test
    @DisplayName("测试递归冒泡排序改进版")
    public void test2(){
        int[] expected = {1,2,3,4,5,6};

        int[] a1 = {2,4,1,3,5,6};
        RecursionBubbleSort.sort1(a1);
        assertArrayEquals(expected,a1);

        int[] a2 = {6,5,1,3,4,2};
        RecursionBubbleSort.sort1(a2);
        assertArrayEquals(expected,a2);

        int[] a3 = {2,1,3,4,5,6};
        RecursionBubbleSort.sort1(a3);
        assertArrayEquals(expected,a3);
    }


}