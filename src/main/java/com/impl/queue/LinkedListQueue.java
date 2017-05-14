package com.impl.queue;

import com.dejected.ConsumerQueue;
import com.dejected.Tuple;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created on 14/05/17 by dark magic.
 */
public class LinkedListQueue<T> implements ConsumerQueue<T> {
    private final int size;
    private final LinkedBlockingQueue<Tuple<T>> linkedList;

    public LinkedListQueue(int size) {
        this.size = size;
        linkedList = new LinkedBlockingQueue<Tuple<T>>(size);
    }

    @Override
    public Tuple<T> take() throws InterruptedException {
        return linkedList.take();
    }

    @Override
    public void offer(Tuple tuple) {
        linkedList.offer(tuple);
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
