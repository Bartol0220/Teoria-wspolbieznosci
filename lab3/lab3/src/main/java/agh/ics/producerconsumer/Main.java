package agh.ics.producerconsumer;

public class Main {
    static void main(String[] args) {
        startProblem(1, 1);
        startProblem(10, 4);
        startProblem(7, 7);
        startProblem(4, 10);
    }

    static void startProblem(int numberOfProducers, int numberOfConsumers){
        System.out.println("Producer–consumer problem");
        System.out.println("Number of producers: " + numberOfProducers + "\nNumber of consumers: " + numberOfConsumers);
    }
}
