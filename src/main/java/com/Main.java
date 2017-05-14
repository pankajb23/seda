package com;

import com.dejected.WorkerContainer;
import com.pojo.Data;

/**
 * Created on 14/05/17 by dark magic.
 */
public class Main {
    public static void main(String[] args) {
        ContainerAbstractFactory factory = new ContainerAbstractFactoryImpl();
        WorkerContainer<Data> startContainer = factory.createContainer(ContainerAbstractFactoryImpl.ContainerType.START,
                4, ContainerAbstractFactoryImpl.ConsumerQueueType.Linked);

        WorkerContainer<Data> addContainer = factory.createContainer(ContainerAbstractFactoryImpl.ContainerType.ADDITION,
                4, ContainerAbstractFactoryImpl.ConsumerQueueType.Linked);

        WorkerContainer<Data> multiplContainer = factory.createContainer(ContainerAbstractFactoryImpl.ContainerType.MULTIPLY,
                4, ContainerAbstractFactoryImpl.ConsumerQueueType.Linked);


        WorkerContainer<Data> powerContainer = factory.createContainer(ContainerAbstractFactoryImpl.ContainerType.POWER,
                4, ContainerAbstractFactoryImpl.ConsumerQueueType.Linked);


        WorkerContainer<Data> postProcessingContainer = factory.createContainer(ContainerAbstractFactoryImpl.ContainerType.POST,
                4, ContainerAbstractFactoryImpl.ConsumerQueueType.Linked);

        startContainer.to(addContainer).to(multiplContainer).to(powerContainer).to(postProcessingContainer);

        startContainer.start();
    }
}
