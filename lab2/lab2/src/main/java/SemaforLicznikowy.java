public class SemaforLicznikowy {
    private int stan = 0;
    private final int liczbaZasobow;

    public SemaforLicznikowy(int liczbaZasobow) {
        this.liczbaZasobow = liczbaZasobow;
    }

    public synchronized void P() throws InterruptedException {
        while (stan >= liczbaZasobow) {
            wait();
        }
        stan += 1;
    }

    public synchronized void V() {
        if (stan >= 0) {
            stan -= 1;
            notifyAll();
        }
    }
}
