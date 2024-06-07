package algorithms.recursion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursionInsertSortTest {

    @Test
    @DisplayName("test RecursionInsertSort")
    public void test1(){
        int[] expected = {1,2,3,4,5};
        int[] a1 = {2,4,1,5,3};
        RecursionInsertSort.sort(a1);
        assertArrayEquals(expected,a1);

        int[] a2 = {1,3,4,5,2};
        RecursionInsertSort.sort(a2);
        assertArrayEquals(expected,a2);
    }

}