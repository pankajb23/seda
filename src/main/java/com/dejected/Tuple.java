package com.dejected;

/**
 * Created on 14/05/17 by dark magic.
 */
public class Tuple<T> extends TupleTiming {
    private final int messageID;
    private final T data;

    public Tuple(int messageID, T data, long timing) {
        super(timing);
        this.messageID = messageID;
        this.data = data;
    }

    public int getMessageID() {
        return messageID;
    }

    public T getData() {
        return data;
    }
}
