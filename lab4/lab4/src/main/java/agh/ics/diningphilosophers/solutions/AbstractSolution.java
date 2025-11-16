package agh.ics.diningphilosophers.solutions;


import agh.ics.diningphilosophers.PhilospherListener;
import agh.ics.diningphilosophers.model.Fork;
import agh.ics.diningphilosophers.model.Forks;
import agh.ics.diningphilosophers.model.Philosopher;

public abstract class AbstractSolution implements Solution {
    protected final Forks forks;
    protected final boolean isTimeRandom;
    protected final PhilospherListener listener;

    public AbstractSolution(Forks forks, boolean isTimeRandom, PhilospherListener listener) {
        this.forks = forks;
        this.isTimeRandom = isTimeRandom;
        this.listener = listener;
    }

    public boolean isTimeRandom() {
        return isTimeRandom;
    }

    @Override
    public void endEating(Philosopher philosopher) {
        Fork leftFork = forks.getFork(philosopher.getLeftForkIndex());
        leftFork.releaseFork();
        listener.newInformation("Philosopher " + philosopher.getIndex() + " relased left fork (" + philosopher.getLeftForkIndex() + ").");

        Fork rightFork = forks.getFork(philosopher.getRightForkIndex());
        rightFork.releaseFork();
        listener.newInformation("Philosopher " + philosopher.getIndex() + " relased right fork (" + philosopher.getRightForkIndex() + ").");
    }
}
