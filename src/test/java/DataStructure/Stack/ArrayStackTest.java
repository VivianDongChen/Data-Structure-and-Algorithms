package DataStructure.Stack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {
    @Test
    public void testPush(){
        ArrayStack<Object> stack = new ArrayStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertFalse(stack.push(4));

        assertIterableEquals(List.of(3,2,1),stack);
    }

    @Test
    public void testPop(){
        ArrayStack<Object> stack = new ArrayStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3,stack.pop());
        assertEquals(2,stack.pop());
        assertEquals(1,stack.pop());

        assertNull(stack.pop());
    }

    @Test
    public void testPeek(){
        ArrayStack<Object> stack = new ArrayStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3,stack.peek());
        assertEquals(3,stack.peek());
    }
}
