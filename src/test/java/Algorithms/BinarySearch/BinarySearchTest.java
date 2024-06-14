package Algorithms.BinarySearch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;

import static Algorithms.BinarySearch.BinarySearch.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {
    @Test
    @DisplayName("binarySearchBasic 找到")
    public void test1(){
        int[] a = {7,13,21,30,38,44,52,53};
        assertEquals(0,binarySearchBasic(a, 7));
        assertEquals(1,binarySearchBasic(a, 13));
        assertEquals(2,binarySearchBasic(a, 21));
        assertEquals(3,binarySearchBasic(a, 30));
        assertEquals(4,binarySearchBasic(a, 38));
        assertEquals(5,binarySearchBasic(a, 44));
        assertEquals(6,binarySearchBasic(a, 52));
        assertEquals(7,binarySearchBasic(a, 53));
    }

    @Test
    @DisplayName("binarySearchBasic 没找到")
    public void test2(){
        int[] a = {7,13,21,30,38,44,52,53};
        assertEquals(-1,binarySearchBasic(a, 0));
        assertEquals(-1,binarySearchBasic(a, 15));
        assertEquals(-1,binarySearchBasic(a, 60));
    }

    @Test
    @DisplayName("binarySearchAlternative 找到")
    public void test3(){
        int[] a = {7,13,21,30,38,44,52,53};
        assertEquals(0,binarySearchAlternative(a, 7));
        assertEquals(1,binarySearchAlternative(a, 13));
        assertEquals(2,binarySearchAlternative(a, 21));
        assertEquals(3,binarySearchAlternative(a, 30));
        assertEquals(4,binarySearchAlternative(a, 38));
        assertEquals(5,binarySearchAlternative(a, 44));
        assertEquals(6,binarySearchAlternative(a, 52));
        assertEquals(7,binarySearchAlternative(a, 53));
    }

    @Test
    @DisplayName("binarySearchBasic 没找到")
    public void test4(){
        int[] a = {7,13,21,30,38,44,52,53};
        assertEquals(-1,binarySearchAlternative(a, 0));
        assertEquals(-1,binarySearchAlternative(a, 15));
        assertEquals(-1,binarySearchAlternative(a, 60));
    }

    @Test
    @DisplayName("binarySearchBalanced 找到")
    public void test5(){
        int[] a = {7,13,21,30,38,44,52,53};
        assertEquals(0,binarySearchBalanced(a, 7));
        assertEquals(1,binarySearchBalanced(a, 13));
        assertEquals(2,binarySearchBalanced(a, 21));
        assertEquals(3,binarySearchBalanced(a, 30));
        assertEquals(4,binarySearchBalanced(a, 38));
        assertEquals(5,binarySearchBalanced(a, 44));
        assertEquals(6,binarySearchBalanced(a, 52));
        assertEquals(7,binarySearchBalanced(a, 53));
    }

    @Test
    @DisplayName("binarySearchBalanced 没找到")
    public void test6(){
        int[] a = {7,13,21,30,38,44,52,53};
        assertEquals(-1,binarySearchBalanced(a, 0));
        assertEquals(-1,binarySearchBalanced(a, 15));
        assertEquals(-1,binarySearchBalanced(a, 60));
    }

    @Test
    @DisplayName("BinarySearch Java版")
    public void test7(){
        int[] a = {2,5,8};
        int target = 4;

        int i = Arrays.binarySearch(a, target);
        System.out.println(i);

        if(i < 0){
            int insertIndex = Math.abs(i+1); //插入点索引
            int [] b = new int[a.length +1];
            System.arraycopy(a,0,b,0,insertIndex);

            b[insertIndex] = target;

            System.arraycopy(a,insertIndex,b,insertIndex+1,a.length-insertIndex);
            System.out.println(Arrays.toString(b));
        }
    }

    @Test
    @DisplayName("binarySearchLeftmost1 返回 -1")
    public void test8(){
        int[] a = {1,2,4,4,4,5,6,7};
        assertEquals(0,binarySearchLeftmost1(a, 1));
        assertEquals(1,binarySearchLeftmost1(a, 2));
        assertEquals(2,binarySearchLeftmost1(a, 4));
        assertEquals(5,binarySearchLeftmost1(a, 5));
        assertEquals(6,binarySearchLeftmost1(a, 6));
        assertEquals(7,binarySearchLeftmost1(a, 7));

        assertEquals(-1,binarySearchLeftmost1(a,0));
        assertEquals(-1,binarySearchLeftmost1(a,3));
        assertEquals(-1,binarySearchLeftmost1(a,8));
    }

    @Test
    @DisplayName("binarySearchRightmost1 返回 -1")
    public void test9(){
        int[] a = {1,2,4,4,4,5,6,7};
        assertEquals(0,binarySearchRightmost1(a, 1));
        assertEquals(1,binarySearchRightmost1(a, 2));
        assertEquals(4,binarySearchRightmost1(a, 4));
        assertEquals(5,binarySearchRightmost1(a, 5));
        assertEquals(6,binarySearchRightmost1(a, 6));
        assertEquals(7,binarySearchRightmost1(a, 7));

        assertEquals(-1,binarySearchRightmost1(a,0));
        assertEquals(-1,binarySearchRightmost1(a,3));
        assertEquals(-1,binarySearchRightmost1(a,8));
    }

    @Test
    @DisplayName("binarySearchLeftmost2 返回大于等于目标值的最靠左索引")
    public void test10(){
        int[] a = {1,2,4,4,4,5,6,7};
        assertEquals(0,binarySearchLeftmost2(a, 1));
        assertEquals(1,binarySearchLeftmost2(a, 2));
        assertEquals(2,binarySearchLeftmost2(a, 4));
        assertEquals(5,binarySearchLeftmost2(a, 5));
        assertEquals(6,binarySearchLeftmost2(a, 6));
        assertEquals(7,binarySearchLeftmost2(a, 7));

        assertEquals(0,binarySearchLeftmost2(a,0));
        assertEquals(2,binarySearchLeftmost2(a,3));
        assertEquals(8,binarySearchLeftmost2(a,8));
    }

    @Test
    @DisplayName("binarySearchRightmost2 返回小于等于目标值的最靠右索引")
    public void test11(){
        int[] a = {1,2,4,4,4,5,6,7};
        assertEquals(0,binarySearchRightmost2(a, 1));
        assertEquals(1,binarySearchRightmost2(a, 2));
        assertEquals(4,binarySearchRightmost2(a, 4));
        assertEquals(5,binarySearchRightmost2(a, 5));
        assertEquals(6,binarySearchRightmost2(a, 6));
        assertEquals(7,binarySearchRightmost2(a, 7));

        assertEquals(-1,binarySearchRightmost2(a,0));
        assertEquals(1,binarySearchRightmost2(a,3));
        assertEquals(7,binarySearchRightmost2(a,8));
    }







}