package Algorithms.Recursion.MultiRecursion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    @DisplayName("Test fibonacciMemorization")
    public void test(){
        assertEquals(1,Fibonacci.fibonacciMemorization(2));
        assertEquals(2,Fibonacci.fibonacciMemorization(3));
        assertEquals(3,Fibonacci.fibonacciMemorization(4));
        assertEquals(5,Fibonacci.fibonacciMemorization(5));
        assertEquals(8,Fibonacci.fibonacciMemorization(6));
        assertEquals(13,Fibonacci.fibonacciMemorization(7));
        assertEquals(21,Fibonacci.fibonacciMemorization(8));
        assertEquals(34,Fibonacci.fibonacciMemorization(9));
        assertEquals(55,Fibonacci.fibonacciMemorization(10));
        assertEquals(89,Fibonacci.fibonacciMemorization(11));
        assertEquals(144,Fibonacci.fibonacciMemorization(12));
        assertEquals(233,Fibonacci.fibonacciMemorization(13));
    }

}