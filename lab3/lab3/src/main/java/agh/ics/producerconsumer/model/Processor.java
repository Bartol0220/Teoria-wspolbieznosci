package agh.ics.producerconsumer.model;

import java.util.Random;

public class Processor extends Thread {
    private final Buffer readBuffer;
    private final Buffer writeBuffer;
    private final long delay;

    public Processor(Buffer readBuffer, Buffer writeBuffer) {
        this.readBuffer = readBuffer;
        this.writeBuffer = writeBuffer;
        Random random = new Random();
        delay = random.nextLong(96) + 5;
    }

    private int process(int data) {
        data++;
        try {
            sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void run() {
        while (true) {
            int res = readBuffer.get();
            if (res == -1) {
                writeBuffer.stopProducing();
                break;
            }
            res = process(res);
            writeBuffer.put(res);
        }
    }
}
