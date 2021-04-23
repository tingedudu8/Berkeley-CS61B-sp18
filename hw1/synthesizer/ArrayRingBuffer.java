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
        fillCount += 1;
        rb[last] = x;
        last += 1;
        if (last == capacity) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T de = rb[first];
        rb[first] = null;
        first += 1;
        fillCount -= 1;
        if (first == capacity) {
            first = 0;
        }
        return de;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        T item = rb[first];
        return item;
    }

    private class QueueIterator implements Iterator<T> {
        private int wizPos = first;
        private int num = fillCount();

        @Override
        public boolean hasNext() {
            return num > 0;
        }

        @Override
        public T next() {
            T item = rb[wizPos];
            wizPos += 1;
            if (wizPos == capacity) {
                wizPos = 0;
            }
            num -= 1;
            return item;
        }

    }
    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

}
