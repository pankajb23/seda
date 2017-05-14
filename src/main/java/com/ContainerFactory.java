package com;

import com.dejected.ConsumerQueue;
import com.dejected.WorkerContainer;
import com.dejected.WorkerProcessor;
import com.impl.queue.ArrayListQueue;
import com.impl.queue.LinkedListQueue;
import com.impl.worker.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 14/05/17 by dark magic.
 */
public class ContainerFactory {
    public static WorkerContainer createContainer(ContainerType containerType, int parallism,
                                                  ConsumerQueueType queueType) {
        List<WorkerProcessor> workerProcessors = new ArrayList<>(parallism);
        switch (containerType) {
            case START:
                workerProcessors = getPreProcessor(parallism);
                break;
            case ADDITION:
                workerProcessors = getADDProcessor(parallism);
                break;
            case MULTIPLY:
                workerProcessors = getMULTIProcessor(parallism);
                break;
            case POWER:
                workerProcessors = getPOWProcessor(parallism);
                break;
            case POST:
                workerProcessors = getPOSTProcessor(parallism);
                break;
            default:
                throw new RuntimeException("Buggy call");
        }
        return new WorkerContainer(workerProcessors, createConsumerQueue(queueType));
    }

    public static ConsumerQueue createConsumerQueue(ConsumerQueueType consumerQueueType) {
        switch (consumerQueueType) {
            case ARRAY:
                return new ArrayListQueue(10);
            case Linked:
                return new LinkedListQueue(10);
            default:
                throw new RuntimeException("Buggy code");
        }
    }

    private static List<WorkerProcessor> getPreProcessor(int parralism) {
        List<WorkerProcessor> list = new ArrayList<>();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < parralism; i++) {
            list.add(new PreProcessorWorker(lock));
        }
        return list;
    }

    private static List<WorkerProcessor> getADDProcessor(int parralism) {
        List<WorkerProcessor> list = new ArrayList<>();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < parralism; i++) {
            list.add(new AdditionWorker(lock));
        }
        return list;
    }

    private static List<WorkerProcessor> getMULTIProcessor(int parralism) {
        List<WorkerProcessor> list = new ArrayList<>();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < parralism; i++) {
            list.add(new MultiplierWorker(lock));
        }
        return list;
    }

    private static List<WorkerProcessor> getPOSTProcessor(int parralism) {
        List<WorkerProcessor> list = new ArrayList<>();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < parralism; i++) {
            list.add(new PostProcessingLogingWorker(lock));
        }
        return list;
    }

    private static List<WorkerProcessor> getPOWProcessor(int parralism) {
        List<WorkerProcessor> list = new ArrayList<>();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < parralism; i++) {
            list.add(new PowerWorker(lock));
        }
        return list;
    }

    public enum ContainerType {
        START,
        ADDITION,
        MULTIPLY,
        POWER,
        POST
    }

    public enum ConsumerQueueType {
        ARRAY,
        Linked
    }
}
