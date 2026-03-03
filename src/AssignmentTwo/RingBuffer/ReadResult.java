package AssignmentTwo.RingBuffer;

public final class ReadResult<T> {
    private final boolean empty;
    private final T value;
    private final long missedCount;

    private ReadResult(boolean empty, T value, long missedCount) {
        this.empty = empty;
        this.value = value;
        this.missedCount = missedCount;
    }

    public static <T> ReadResult<T> empty(long missedCount) {
        return new ReadResult<>(true, null, missedCount);
    }

    public static <T> ReadResult<T> value(T value, long missedCount) {
        return new ReadResult<>(false, value, missedCount);
    }

    public boolean isEmpty() {
        return empty;
    }

    public T getValue() {
        if (empty) throw new IllegalStateException("No value: result is empty");
        return value;
    }

    public long getMissedCount() {
        return missedCount;
    }

    @Override
    public String toString() {
        if (empty) {
            return "ReadResult{empty=true, missedCount=" + missedCount + "}";
        }
        return "ReadResult{empty=false, value=" + value + ", missedCount=" + missedCount + "}";
    }
}