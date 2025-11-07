package agh.ics.producerconsumer.model;

import java.util.LinkedList;

class Buffer {
    private final int capacity;
    private final LinkedList<Integer> buffer;
    private int currentSize = 0;

    public Buffer(int capacity) {
        this.capacity = capacity;
        buffer = new LinkedList<>();
    }

    public synchronized void put(int i) {
        while (currentSize >= capacity) {
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
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int res = buffer.getLast();
        currentSize -= 1;
        notifyAll();
        return res;
    }
}
