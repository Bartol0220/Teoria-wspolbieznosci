package agh.ics.producerconsumer.model;

import java.util.LinkedList;

public class Buffer {
    private final int capacity;
    private final LinkedList<Integer> buffer;
    private int currentSize = 0;
    private boolean stoppedProducing = false;
    private boolean stoppedConsuming = false;

    public Buffer(int capacity) {
        this.capacity = capacity;
        buffer = new LinkedList<>();
    }

    public synchronized void put(int i) {
        while (currentSize >= capacity) {
            if (stoppedConsuming) return;
            try {
                wait();
            }  catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer.addFirst(i);
        currentSize += 1;
        notifyAll();
    }

    public synchronized int get() {
        while (currentSize <= 0) {
            if (stoppedProducing) return -1;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int res = buffer.removeFirst();
        currentSize -= 1;
        notifyAll();
        return res;
    }

    public synchronized void stopProducing() {
        stoppedProducing = true;
        notifyAll();
    }

    public synchronized void stopConsuming() {
        stoppedConsuming = true;
        notifyAll();
    }
}
