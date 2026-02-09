package classwork;

import java.util.ArrayList;
import java.util.List;

public class RingBuffer {
    private List<Integer> buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;
    private int capacity;


    public RingBuffer(int capacity) {
        this.capacity = capacity;
        buffer = new ArrayList<>(capacity);

        //pre-fill to fixed size
        for (int i = 0; i < capacity; i++) {
            buffer.add(0);
        }
    }
    public void add(int value) {
        if (count == capacity) {
            System.out.println("Buffer full");
            return;
        }

        head = (head - 1 + capacity) % capacity;
        buffer.set(head, value);
        count++;
    }
    public void write(int value) {
        if (count == capacity) {
            System.out.println("Buffer full");
            return;
        }

        buffer.set(tail, value);
        tail = (tail + 1) % capacity;
        count++;
    }

    public int read() {
        if (count == 0) {
            System.out.println("Buffer empty");
            return -1;
        }

        int value = buffer.get(head);
        head = (head + 1) % capacity;
        count--;
        return value;
    }


}
