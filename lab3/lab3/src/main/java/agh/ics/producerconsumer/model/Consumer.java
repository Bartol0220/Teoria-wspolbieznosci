package agh.ics.producerconsumer.model;

public class Consumer extends Thread {
    private final Buffer buf;

    public Consumer(Buffer buf) {
        this.buf = buf;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            int res = buf.get();
            if (res == -1) break;
            System.out.println(res);
        }
    }
}