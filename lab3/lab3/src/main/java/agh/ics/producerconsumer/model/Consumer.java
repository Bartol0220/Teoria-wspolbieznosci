package agh.ics.producerconsumer.model;

class Consumer extends Thread {
    private final Buffer buf;

    public Consumer(Buffer buf) {
        this.buf = buf;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println(buf.get());
        }
    }
}