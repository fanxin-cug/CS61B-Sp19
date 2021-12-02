package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(4);
        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        assertEquals(3,(int)arb.peek());
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        assertTrue(arb.isFull());
        arb.dequeue();
        arb.enqueue(7);
        assertTrue(arb.isFull());
    }
}
