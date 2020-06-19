package synthesizer;
import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = plusOne(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T firstItem = rb[first];
        rb[first] = null;
        first = plusOne(first);
        fillCount -= 1;
        return firstItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T firstItem = rb[first];
        return firstItem;
    }

    private int plusOne(int index) {
        return (index + 1) % capacity();
    }

    @Override
    public Iterator<T> iterator() {
        return new ARBIterator();
    }

    public class ARBIterator implements Iterator<T> {

        private int numRemain;
        private int ptr;

        public ARBIterator() {
            numRemain = fillCount();
            ptr = first;
        }

        @Override
        public boolean hasNext() {
            return numRemain > 0;
        }

        @Override
        public T next() {
            T item = rb[ptr];
            ptr = plusOne(ptr);
            numRemain -= 1;
            return item;
        }
    }
}
