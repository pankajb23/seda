package com.dejected;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 14/05/17 by dark magic.
 */
public class WorkerContainer<T> {
    /**
     * list of worker threads which are in this specific container;
     */
    private final List<? extends WorkerProcessor> containerWorkerThread;
    private final ConsumerQueue<T> containersQueue;
    private final ExecutorService executors;
    private WorkerContainer nextContainer;

    public WorkerContainer(List<? extends WorkerProcessor> containerWorkerThread,
                           ConsumerQueue<T> containersQueue) {

        this.containerWorkerThread = containerWorkerThread;
        this.containersQueue = containersQueue;
        executors = Executors.newFixedThreadPool(this.containerWorkerThread.size());
    }

    public WorkerContainer to(WorkerContainer workerContainer) {
        this.nextContainer = workerContainer;
        return this.nextContainer;
    }


    public ConsumerQueue<T> getContainersQueue() {
        return this.containersQueue;
    }

    public void start() {
        if (this.nextContainer != null)
            this.nextContainer.start();
        for (WorkerProcessor workerProcessor : containerWorkerThread) {
            workerProcessor.setConsumerQueue(this.containersQueue);
            if (this.nextContainer != null)
                workerProcessor.setProducerQueue(this.nextContainer.containersQueue);
            executors.submit(workerProcessor);
        }
    }
}
