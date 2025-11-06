package agh.ics.diningphilosophers.solutions;

import agh.ics.diningphilosophers.PhilospherListener;
import agh.ics.diningphilosophers.model.Fork;
import agh.ics.diningphilosophers.model.Forks;
import agh.ics.diningphilosophers.model.Philosopher;

import java.util.concurrent.atomic.AtomicInteger;

public class SolutionWithWaiter extends AbstractSolution {
    private final int maximumNumberOfEatingPhilosophers;
    private final AtomicInteger numberOfEatingPhilosophers = new AtomicInteger(0);

    public SolutionWithWaiter(Forks forks, int numberOfPhilosophers, boolean isTimeRandom, PhilospherListener listener) {
        super(forks, isTimeRandom, listener);
        this.maximumNumberOfEatingPhilosophers = numberOfPhilosophers - 1;
    }

    @Override
    public boolean startEating(Philosopher philosopher) {
        Fork leftFork = forks.getFork(philosopher.getLeftForkIndex());
        Fork rightFork = forks.getFork(philosopher.getRightForkIndex());

        synchronized (numberOfEatingPhilosophers) {
            while (numberOfEatingPhilosophers.get() >= maximumNumberOfEatingPhilosophers) {
                try {
                    numberOfEatingPhilosophers.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            numberOfEatingPhilosophers.incrementAndGet();
            listener.newInformation("Now number of eating philosophers = " + numberOfEatingPhilosophers.get());
        }
        leftFork.useFork();
        listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up left fork (" + philosopher.getLeftForkIndex() + ").");

        rightFork.useFork();
        listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up right fork (" + philosopher.getRightForkIndex() + ").");
        return true;
    }

    @Override
    public void endEating(Philosopher philosopher) {
        super.endEating(philosopher);

        synchronized (numberOfEatingPhilosophers) {
            numberOfEatingPhilosophers.decrementAndGet();
            numberOfEatingPhilosophers.notifyAll();
        }
    }

    @Override
    public String getName() {
        return "Rozwiązanie z arbitrem";
    }
}
