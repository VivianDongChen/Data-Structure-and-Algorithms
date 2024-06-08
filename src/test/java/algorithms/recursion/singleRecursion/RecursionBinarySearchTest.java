package algorithms.recursion.singleRecursion;

import algorithms.recursion.singleRecursion.RecursionBinarySearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecursionBinarySearchTest {

    @Test
    @DisplayName("测试 RecursionBinarySearch")
    public void test(){
        int[] a = {7,13,21,30,38,44,52,53};
        assertEquals(0, RecursionBinarySearch.search(a,7));
        assertEquals(1, RecursionBinarySearch.search(a,13));
        assertEquals(2, RecursionBinarySearch.search(a,21));
        assertEquals(3, RecursionBinarySearch.search(a,30));
        assertEquals(4, RecursionBinarySearch.search(a,38));
        assertEquals(5, RecursionBinarySearch.search(a,44));
        assertEquals(6, RecursionBinarySearch.search(a,52));
        assertEquals(7, RecursionBinarySearch.search(a,53));

        assertEquals(-1, RecursionBinarySearch.search(a,0));
        assertEquals(-1, RecursionBinarySearch.search(a,15));
        assertEquals(-1, RecursionBinarySearch.search(a,60));
    }




}