public class Semafor {
    private boolean stan = false;

    public synchronized void P() throws InterruptedException {
        while (stan) {
            wait();
        }
        stan = true;
    }

    public synchronized void V() {
        stan = false;
        notifyAll();
    }
}
