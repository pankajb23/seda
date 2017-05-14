package com;

import com.dejected.ConsumerQueue;
import com.dejected.WorkerContainer;

/**
 * Created on 14/05/17 by dark magic.
 */
public interface ContainerAbstractFactory {
    WorkerContainer createContainer(ContainerType containerType, int parallism,
                                    ConsumerQueueType queueType);

    ConsumerQueue createConsumerQueue(ConsumerQueueType consumerQueueType);

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
