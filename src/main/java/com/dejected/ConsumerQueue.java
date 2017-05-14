package com.dejected;

/**
 * Created on 14/05/17 by dark magic.
 */
public interface ConsumerQueue<T> {
    Tuple<T> take() throws InterruptedException;

    void offer(Tuple tuple);

    int size();

    boolean isEmpty();
}
