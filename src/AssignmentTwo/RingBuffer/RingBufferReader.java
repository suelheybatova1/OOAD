package AssignmentTwo.RingBuffer;

public final class RingBufferReader<T> {
    private final RingBuffer<T> buffer;
    private long nextSeqToRead;

    RingBufferReader(RingBuffer<T> buffer, long startSeq) {
        this.buffer = buffer;
        this.nextSeqToRead = startSeq;
    }

    public long getNextSeqToRead() {
        return nextSeqToRead;
    }

    //read next item. Skips overwritten data if needed.
    public ReadResult<T> read() {
        long missed = 0L;
        T value;

        synchronized (buffer) {
            long oldest = buffer.getOldestAvailableSeq();
            long end = buffer.getNextWriteSeq();

            //nothing new to read
            if (nextSeqToRead >= end) {
                return ReadResult.empty(0);
            }

            //reader is too slow: data overwritten
            if (nextSeqToRead < oldest) {
                missed = oldest - nextSeqToRead;
                nextSeqToRead = oldest;

                //after skipping, maybe still nothing
                if (nextSeqToRead >= end) {
                    return ReadResult.empty(missed);
                }
            }

            //now nextSeqToRead is valid
            value = buffer.readAtSeq(nextSeqToRead);
            nextSeqToRead++;
        }

        return ReadResult.value(value, missed);
    }
}