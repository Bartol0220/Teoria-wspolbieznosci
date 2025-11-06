package agh.ics.diningphilosophers.solutions;

import agh.ics.diningphilosophers.PhilospherListener;
import agh.ics.diningphilosophers.model.Fork;
import agh.ics.diningphilosophers.model.Forks;
import agh.ics.diningphilosophers.model.Philosopher;

public class SolutionNaive extends AbstractSolution {

    public SolutionNaive(Forks forks, boolean isTimeRandom, PhilospherListener listener) {
        super(forks, isTimeRandom, listener);
    }

    @Override
    public boolean startEating(Philosopher philosopher) {
        Fork leftFork = forks.getFork(philosopher.getLeftForkIndex());
        leftFork.useFork();
        listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up left fork (" + philosopher.getLeftForkIndex() + ").");

        Fork rightFork = forks.getFork(philosopher.getRightForkIndex());
        rightFork.useFork();
        listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up right fork (" + philosopher.getRightForkIndex() + ").");

        return true;
    }

    @Override
    public String getName() {
        return "Rozwiązanie naiwne";
    }
}
