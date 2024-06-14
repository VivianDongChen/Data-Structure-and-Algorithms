package DataStructure.Queue;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueue2Test {

    @Test
    public void testOffer(){
        ArrayQueue2 queue = new ArrayQueue2<>(5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        assertIterableEquals(List.of(1,2,3,4,5),queue);

    }

    @Test
    public void testPeek(){
        ArrayQueue2 queue = new ArrayQueue2<>(5);
        assertNull(queue.peek());
        queue.offer(1);
        assertEquals(1,queue.peek());
        queue.offer(2);
        assertEquals(1,queue.peek());
    }

    @Test
    public void testPoll(){
        ArrayQueue2 queue = new ArrayQueue2<>(5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        assertEquals(1,queue.poll());
        assertEquals(2,queue.poll());
        assertEquals(3,queue.poll());
        assertEquals(4,queue.poll());
        assertEquals(5,queue.poll());
        assertNull(queue.poll());

    }

    @Test
    public void testOffLimit(){
        ArrayQueue2 queue = new ArrayQueue2<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        assertFalse(queue.offer(4));
        assertFalse(queue.offer(5));

        assertIterableEquals(List.of(1,2,3),queue);
    }

}
