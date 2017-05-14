package com;

import com.dejected.WorkerContainer;
import com.pojo.Data;

/**
 * Created on 14/05/17 by dark magic.
 */
public class Main {
    public static void main(String[] args) {
        WorkerContainer<Data> startContainer = ContainerFactory.createContainer(ContainerFactory.ContainerType.START,
                4, ContainerFactory.ConsumerQueueType.Linked);

        WorkerContainer<Data> addContainer = ContainerFactory.createContainer(ContainerFactory.ContainerType.ADDITION,
                4, ContainerFactory.ConsumerQueueType.Linked);

        WorkerContainer<Data> multiplContainer = ContainerFactory.createContainer(ContainerFactory.ContainerType.MULTIPLY,
                4, ContainerFactory.ConsumerQueueType.Linked);


        WorkerContainer<Data> powerContainer = ContainerFactory.createContainer(ContainerFactory.ContainerType.POWER,
                4, ContainerFactory.ConsumerQueueType.Linked);


        WorkerContainer<Data> postProcessingContainer = ContainerFactory.createContainer(ContainerFactory.ContainerType.POST,
                4, ContainerFactory.ConsumerQueueType.Linked);

        startContainer.to(addContainer).to(multiplContainer).to(powerContainer).to(postProcessingContainer);

        startContainer.start();
    }
}
