package ua.edu.ucu.utils;

import ua.edu.ucu.utils.immutable.ImmutableArrayList;

import java.util.Iterator;

public class Queue {
    private ImmutableArrayList immutableArrayList;

    public Queue() {
        this.setImmutableLinkedList(new ImmutableArrayList());
    }

    public Queue(ImmutableArrayList arrayList) {
        this.setImmutableLinkedList(arrayList);
    }

    public Object peek() {
        return this.getImmutableArrayList().getFirst();
    }

    public Object dequeue() {
        Object firstObject = this.getImmutableArrayList().getFirst();
        this.setImmutableLinkedList(this.getImmutableArrayList().removeFirst());
        return firstObject;
    }

    public void enqueue(Object e) {
        this.setImmutableLinkedList(this.getImmutableArrayList().addLast(e));
    }

    public ImmutableArrayList getImmutableArrayList() {
        return immutableArrayList;
    }

    public void setImmutableLinkedList(ImmutableArrayList immutableLinkedList) {
        this.immutableArrayList = immutableLinkedList;
    }
}
