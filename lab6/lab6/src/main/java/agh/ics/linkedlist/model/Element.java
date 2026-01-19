package agh.ics.linkedlist.model;

import static java.lang.Thread.sleep;

public class Element {
    private Element next = null;
    private Object object;
    private boolean isUsed = false;

    public Element(Object object) {
        this.object = object;
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
        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.object.equals(object);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Element getNext() {
        return next;
    }

    public void setNext(Element next) {
        this.next = next;
    }
}
