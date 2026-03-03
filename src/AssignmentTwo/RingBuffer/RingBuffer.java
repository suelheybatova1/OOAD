package AssignmentTwo.RingBuffer;

import java.util.Objects;

public final class RingBuffer<T> {
    private final int capacity;
    private final Object[] buffer;

    //global write counter
    //slot index = seq % capacity
    private long nextWriteSeq;

    public RingBuffer(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.capacity = capacity;
        this.buffer = new Object[capacity];
        this.nextWriteSeq = 0L;
    }

    public int capacity() {
        return capacity;
    }

    //returns next write sequence (current end)
    public synchronized long getNextWriteSeq() {
        return nextWriteSeq;
    }

    //returns oldest sequence still in buffer.
    public synchronized long getOldestAvailableSeq() {
        long oldest = nextWriteSeq - capacity;
        return Math.max(0L, oldest);
    }

    //writes item (may overwrite old data)
    public synchronized long write(T item) {
        Objects.requireNonNull(item, "item cannot be null");
        long seq = nextWriteSeq;
        int index = (int) (seq % capacity);
        buffer[index] = item;
        nextWriteSeq++;
        return seq;
    }

    //reads item by sequence (used by reader)
    synchronized T readAtSeq(long seq) {
        // caller must ensure seq is in [oldestAvailable, nextWriteSeq)
        int index = (int) (seq % capacity);
        @SuppressWarnings("unchecked")
        T value = (T) buffer[index];
        return value;
    }

    //creates reader starting from oldest item
    public RingBufferReader<T> createReader() {
        return createReaderAtOldest();
    }

    public RingBufferReader<T> createReaderAtOldest() {
        long start;
        synchronized (this) {
            start = getOldestAvailableSeq();
        }
        return new RingBufferReader<>(this, start);
    }

    //creates reader starting from current position
    public RingBufferReader<T> createReaderAtLatest() {
        long start;
        synchronized (this) {
            start = getNextWriteSeq();
        }
        return new RingBufferReader<>(this, start);
    }
}