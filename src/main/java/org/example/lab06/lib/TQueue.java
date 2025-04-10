package org.example.lab06.lib;


import org.example.lab06.inter.InterQueueIterable;

import java.util.Iterator;

public class TQueue<E> implements InterQueueIterable<E> {
    private TQueueNode<E> head;
    private TQueueNode<E> tail;

    public TQueue() {
        head = null;
        tail = null;
    }

    @Override
    public boolean put(E element) {
        TQueueNode<E> ref;
        try {
            ref = new TQueueNode<E>(element);
        } catch (Exception e) {
            return false;
        }
        if (head == null) {
            head = ref;
            tail = ref;
        } else {
            tail.setPtr(ref);
            tail = tail.getPtr();
        }
        return true;
    }

    @Override
    public E top() {
        return head == null ? null : head.getData();
    }

    @Override
    public E get() {
        if (isEmpty()) {
            return null;
        }

        E data = head.getData();
        head = head.getPtr();

        if (head == null) {
            tail = null;
        }

        return data;
    }

    @Override
    public boolean pop() {
        if (head == null) {
            return false;
        }

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getPtr();
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        int count = 0;
        TQueueNode<E> current = head;
        while (current != null) {
            count++;
            current = current.getPtr();
        }
        return count;
    }

    @Override
    public Iterator<E> iterator() {
        return new TQueueIterator<E>(head);
    }
}
