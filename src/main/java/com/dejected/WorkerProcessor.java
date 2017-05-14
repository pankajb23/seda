package com.dejected;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.locks.Lock;

/**
 * Created on 13/05/17 by dark magic.
 */
public abstract class WorkerProcessor<T> implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(WorkerProcessor.class);

    private ConsumerQueue consumerQueue;
    private ConsumerQueue producerQueue;
    private boolean shouldContinue = true;
    private final Lock lock;

    public WorkerProcessor(Lock lock) {
        this.lock = lock;
    }

    final public void run() {
        while (shouldContinue) {
            try {
                Optional<Tuple> tuple = getTuple();
                if (!tuple.isPresent()) {
                    LOG.info("No Tuple is present in queue for thread {} sleeping for 1s", Thread.currentThread().getId());
                } else {
                    LOG.info("Tuple in process {}, with thread {}", tuple.get().getMessageID(), Thread.currentThread().getId());
                    process(tuple.get());
                }
            } catch (InterruptedException e) {
                LOG.error("Error occurred ", e);
            }
        }
    }

    protected abstract void process(Tuple tuple);

    final protected void publish(Tuple tuple) {
        producerQueue.offer(tuple);
    }

    public Optional<Tuple> getTuple() throws InterruptedException {
        try {
            lock.lock();
            Tuple tuple = null;
            if (!consumerQueue.isEmpty())
                tuple = consumerQueue.take();
            return Optional.ofNullable(tuple);
        } finally {
            lock.unlock();
        }
    }

    final public void setConsumerQueue(ConsumerQueue consumerQueue) {
        this.consumerQueue = consumerQueue;
    }

    final public void setProducerQueue(ConsumerQueue producerQueue) {
        this.producerQueue = producerQueue;
    }
}
