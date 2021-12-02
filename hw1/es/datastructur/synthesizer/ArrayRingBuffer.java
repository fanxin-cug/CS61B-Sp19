package es.datastructur.synthesizer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount++;
        last++;
        if(last>=capacity()){
            last-=capacity();
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T res = rb[first];
        rb[first] = null;
        fillCount--;
        first++;
        if(first>=capacity()){
            first-=capacity();
        }
        return res;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int pos;
        public ArrayRingBufferIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < capacity();
        }

        @Override
        public T next() {
            T returnItem = rb[pos];
            pos += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayRingBuffer<?> that = (ArrayRingBuffer<?>) o;
        return first == that.first && last == that.last && fillCount == that.fillCount && Arrays.equals(rb, that.rb);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(first, last, fillCount);
        result = 31 * result + Arrays.hashCode(rb);
        return result;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
