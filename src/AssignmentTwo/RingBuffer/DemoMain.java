package AssignmentTwo.RingBuffer;

public final class DemoMain {
    public static void main(String[] args) {
        RingBuffer<Integer> rb = new RingBuffer<>(4);

        //two independent readers
        RingBufferReader<Integer> rA = rb.createReaderAtOldest();
        RingBufferReader<Integer> rB = rb.createReaderAtOldest();

        //writer writes 1..6 (capacity=4, so overwrite happens after 4)
        for (int i = 1; i <= 6; i++) {
            rb.write(i);
            System.out.println("Wrote: " + i);
        }

        //at this point buffer contains last 4 items: 3,4,5,6 (in ring slots)
        //readers that didn't read earlier may miss 1,2

        System.out.println("\nReader A reads 3 times:");
        for (int i = 0; i < 3; i++) {
            ReadResult<Integer> res = rA.read();
            System.out.println("A -> " + res + (res.isEmpty() ? "" : (" (value=" + res.getValue() + ")")));
        }

        System.out.println("\nReader B reads all available:");
        while (true) {
            ReadResult<Integer> res = rB.read();
            if (res.isEmpty()) {
                System.out.println("B -> " + res);
                break;
            }
            System.out.println("B -> " + res + " (value=" + res.getValue() + ")");
        }

        System.out.println("\nWrite more items (7,8):");
        rb.write(7);
        rb.write(8);

        System.out.println("\nReader A continues:");
        while (true) {
            ReadResult<Integer> res = rA.read();
            if (res.isEmpty()) {
                System.out.println("A -> " + res);
                break;
            }
            System.out.println("A -> " + res + " (value=" + res.getValue() + ")");
        }
    }
}