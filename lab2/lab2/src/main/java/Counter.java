public class Counter {
    private int value = 0;

    public void restart() {
        value = 0;
    }

    public void increment(){
        value++;
    }

    public void decrement(){
        value--;
    }

    public int getValue(){
        return value;
    }

    public void incementSync() {
        synchronized (this) {
            value++;
        }
    }

    public void decrementSync() {
        synchronized (this) {
            value--;
        }
    }
}
