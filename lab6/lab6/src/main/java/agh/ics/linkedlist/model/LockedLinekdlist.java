package agh.ics.linkedlist.model;

public class LockedLinekdlist implements List {
    private final Guardian guardian;
    private boolean isUsed = false;

    public LockedLinekdlist() {
        this.guardian = new Guardian();
    }

    public synchronized boolean isUsed() {
        return isUsed;
    }

    public synchronized void lock() {
        while (isUsed) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isUsed = true;
    }

    public synchronized void release() {
        isUsed = false;
        notifyAll();
    }

    public boolean contains(Object object) {
        lock();
        Element element = guardian.getNext();
        while (element != null) {
            if (element.contains(object)) {
                release();
                return true;
            }
            element = element.getNext();
        }
        release();
        return false;
    }

    public boolean add(Object object) {
        lock();
        Element prev = null;
        Element element = guardian.getNext();

        while (element != null) {
            prev = element;
            element = element.getNext();
        }

        if (prev == null) {
            guardian.setNext(new Element(object));
        } else {
            prev.setNext(new Element(object));
        }

        release();
        return true;
    }
}
