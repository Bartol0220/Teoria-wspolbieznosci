package agh.ics.diningphilosophers.model;

public class Fork {
    private boolean isUsed;

    public Fork(){
        isUsed = false;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public synchronized void useFork(){
        while (isUsed){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isUsed = true;
    }

    public synchronized void releaseFork(){
        isUsed = false;
        notifyAll();
    }

    public synchronized boolean tryAcquire() {
        if (isUsed) {
            return false;
        }
        isUsed = true;
        return true;
    }
}
