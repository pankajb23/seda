package com.impl.worker;

import com.dejected.Tuple;
import com.dejected.WorkerProcessor;
import com.pojo.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * Created on 14/05/17 by dark magic.
 */
public class PreProcessorWorker<T> extends WorkerProcessor<T> {
    private final static Logger LOG = LoggerFactory.getLogger(PreProcessorWorker.class);
    private final Random random = new Random();

    public PreProcessorWorker(Lock lock) {
        super(lock);

    }

    @Override
    public Optional<Tuple> getTuple() {
        try {
            Thread.sleep(1000);
        }catch (Exception ex){
            LOG.error("Tried sleeping for 1 sec");
        }
        Tuple tuple = new Tuple(random.nextInt(), new Data(random.nextInt()), System.currentTimeMillis());
        LOG.info("Tuple messageID {}, data {}, time {}", tuple.getMessageID(), tuple.getData(), tuple.getTiming());
        return Optional.ofNullable(tuple);
    }

    @Override
    protected void process(Tuple tuple) {
        publish(tuple);
    }
}
