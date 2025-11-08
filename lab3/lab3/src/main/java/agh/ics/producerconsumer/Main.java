package agh.ics.producerconsumer;

import agh.ics.producerconsumer.model.Buffer;
import agh.ics.producerconsumer.model.Consumer;
import agh.ics.producerconsumer.model.Processor;
import agh.ics.producerconsumer.model.Producer;

public class Main {
    static void main(String[] args) {
        switch (args[0]) {
            case "1":
                startProducerConsumerProblem(1, 1);

                startProducerConsumerProblem(15, 4);
                startProducerConsumerProblem(7, 7);
                startProducerConsumerProblem(4, 15);
                break;
            case "2":
                pipelineProcessing(2);
                pipelineProcessing(65);
                break;
            default:
                System.out.println("Unknown argument");
        }
    }

    static void startProducerConsumerProblem(int numberOfProducers, int numberOfConsumers){
        Producer[] producers = new Producer[numberOfProducers];
        Consumer[] consumers = new Consumer[numberOfConsumers];

        System.out.println("Producer-consumer problem");
        System.out.println("Number of producers: " + numberOfProducers + "\nNumber of consumers: " + numberOfConsumers);
        Buffer buffer = new Buffer( 10 * numberOfProducers);

        Thread producentsThread = new Thread(() -> {
            for (int i = 0; i < numberOfProducers; i++){
                Producer producer = new Producer(buffer);
                producers[i] = producer;
                producer.start();
            }

            for (int i = 0; i < numberOfProducers; i++){
                try {
                    producers[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Producers done");
            buffer.stopProducing();
        });

        Thread consumersThread = new Thread(() -> {
            for (int i = 0; i < numberOfConsumers; i++){
                Consumer consumer = new Consumer(buffer);
                consumers[i] = consumer;
                consumer.start();
            }

            for (int i = 0; i < numberOfConsumers; i++){
                try {
                    consumers[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Consumers done");
            buffer.stopConsuming();
        });

        producentsThread.start();
        consumersThread.start();

        try {
            producentsThread.join();
            consumersThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void pipelineProcessing(int numberOfProcessors) {
        assert numberOfProcessors >= 2;

        System.out.println("Pipeline processing");
        System.out.println("Number of processors: " + numberOfProcessors);

        Processor[] processors = new Processor[numberOfProcessors];
        Buffer startBuffer = new Buffer( 10);
        Buffer endBuffer = new Buffer( 10);

        Producer producer = new Producer(startBuffer);
        Consumer consumer = new Consumer(endBuffer);

        Buffer prevBuffer = new Buffer( 10);

        Processor firstProcessor = new Processor(startBuffer, prevBuffer);
        processors[0] = firstProcessor;

        for (int i = 0; i < numberOfProcessors - 2; i++){
            Buffer newBuffer = new Buffer( 10 );
            Processor processor = new Processor(prevBuffer, newBuffer);
            processors[i + 1] = processor;
            prevBuffer =  newBuffer;
        }

        Processor lastProcessor = new Processor(prevBuffer, endBuffer);
        processors[numberOfProcessors - 1] = lastProcessor;

        for (int i = 0; i < numberOfProcessors; i++){
            processors[i].start();
        }

        producer.start();
        consumer.start();

        try {
            producer.join();
            startBuffer.stopProducing();
            for (int i = 0; i < numberOfProcessors; i++){
                processors[i].join();
            }
            consumer.join();
            endBuffer.stopConsuming();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
