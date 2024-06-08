package algorithms.recursion.singleRecursion;

import algorithms.recursion.singleRecursion.RecursionInsertSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursionInsertSortTest {

    @Test
    @DisplayName("test sort1")
    public void test1(){
        int[] expected = {1,2,3,4,5};
        int[] a1 = {2,4,1,5,3};
        RecursionInsertSort.sort1(a1);
        assertArrayEquals(expected,a1);

        int[] a2 = {1,3,4,5,2};
        RecursionInsertSort.sort1(a2);
        assertArrayEquals(expected,a2);
    }


    @Test
    @DisplayName("test Sort2")
    public void test2(){
        int[] expected = {1,2,3,4,5};
        int[] a1 = {2,4,1,5,3};
        RecursionInsertSort.sort2(a1);
        assertArrayEquals(expected,a1);

        int[] a2 = {1,3,4,5,2};
        RecursionInsertSort.sort2(a2);
        assertArrayEquals(expected,a2);
    }

}