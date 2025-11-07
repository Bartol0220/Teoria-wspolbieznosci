package agh.ics.producerconsumer.model;

class Producer extends Thread {
    private final Buffer buf;

    public Producer(Buffer buf) {
        this.buf = buf;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            buf.put(i);
        }
    }
}
