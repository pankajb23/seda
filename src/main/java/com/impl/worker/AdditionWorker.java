package com.impl.worker;

import com.dejected.Tuple;
import com.dejected.WorkerProcessor;
import com.pojo.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;

/**
 * Created on 14/05/17 by dark magic.
 */
public class AdditionWorker<T> extends WorkerProcessor<T> {
    private final static Logger LOG = LoggerFactory.getLogger(AdditionWorker.class);

    public AdditionWorker(Lock lock) {
        super(lock);
    }

    @Override
    protected void process(Tuple tuple) {
        Data data = (Data) tuple.getData();
        data.setValue(data.getValue() + 100);
        LOG.info("Data value {} for thread for messsageID {}", data.getValue(), tuple.getMessageID());
        publish(tuple);
    }
}
