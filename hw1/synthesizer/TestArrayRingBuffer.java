package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testIsEmptyIsFull() {
        synthesizer.ArrayRingBuffer<Integer> arb = new synthesizer.ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        assertTrue(arb.isFull());
    }

    @Test
    public void testEnqueueFillCount() {
        synthesizer.ArrayRingBuffer<Integer> arb = new synthesizer.ArrayRingBuffer(10);
        for (int i = 0; i < 5; i++) {
            arb.enqueue(i);
        }
        assertEquals(arb.fillCount(), 5);

        for (int i = 5; i < 10; i++) {
            arb.enqueue(i);
        }
        assertEquals(arb.fillCount(), 10);
    }

    @Test
    public void testDequeueIsEmpty() {
        synthesizer.ArrayRingBuffer<Integer> arb = new synthesizer.ArrayRingBuffer(10);
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(arb.dequeue());
        }
    }



    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
