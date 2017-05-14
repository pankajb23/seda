package com.impl.worker;

import com.dejected.Tuple;
import com.dejected.WorkerProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;

/**
 * Created on 14/05/17 by dark magic.
 */
public class PostProcessingLogingWorker<T> extends WorkerProcessor<T> {
    private static final Logger LOG = LoggerFactory.getLogger(PostProcessingLogingWorker.class);

    public PostProcessingLogingWorker(Lock lock) {
        super(lock);
    }

    @Override
    protected void process(Tuple tuple) {
        LOG.info("Processing complete for tuple ID {}, with initial Time {} with final processing time {}",
                tuple.getMessageID(), tuple.getTiming(), System.currentTimeMillis());
    }
}
