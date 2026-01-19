package agh.ics.linkedlist.model;

public class Linkedlist implements List {
    private final Guardian guardian;

    public Linkedlist() {
        this.guardian = new Guardian();
    }

    public boolean contains(Object object) {
        Element element;
        Element prev;
        synchronized (guardian) {
            element = guardian.getNext();
        }
        while (element != null) {
            element.lock();
            if (element.contains(object)) {
                return true;
            }
            prev = element;
            element = element.getNext();
            prev.release();
        }
        return false;
    }

    public boolean add(Object object) {
        Element element;
        Element prev = null;
        synchronized (guardian) {
            element = guardian.getNext();
        }

        while (element != null) {
            element.lock();
            prev = element;
            element = element.getNext();
            prev.release();
        }

        if (prev == null) {
            synchronized (guardian) {
                guardian.setNext(new Element(object));
            }
        } else {
            prev.lock();
            prev.setNext(new Element(object));
            prev.release();
        }
        return true;
    }
}
