package agh.ics.diningphilosophers.solutions;

import agh.ics.diningphilosophers.PhilospherListener;
import agh.ics.diningphilosophers.model.Fork;
import agh.ics.diningphilosophers.model.Forks;
import agh.ics.diningphilosophers.model.Philosopher;

public class SolutionWithPossibleStarvation extends AbstractSolution {

    public SolutionWithPossibleStarvation(Forks forks, boolean isTimeRandom, PhilospherListener listener) {
        super(forks, isTimeRandom, listener);
    }

    @Override
    public boolean startEating(Philosopher philosopher) {
        Fork leftFork = forks.getFork(philosopher.getLeftForkIndex());
        Fork rightFork = forks.getFork(philosopher.getRightForkIndex());

        if (!leftFork.tryAcquire()) {
            return false;
        }

        if (!rightFork.tryAcquire()) {
            leftFork.relaseFork();
            return false;
        }

        listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up forks (" + philosopher.getLeftForkIndex() + ", " + philosopher.getRightForkIndex() + ").");
        return true;
    }

    @Override
    public void endEating(Philosopher philosopher) {
        Fork leftFork = forks.getFork(philosopher.getLeftForkIndex());
        Fork rightFork = forks.getFork(philosopher.getRightForkIndex());

        rightFork.relaseFork();
        leftFork.relaseFork();
        listener.newInformation("Philosopher " + philosopher.getIndex() + " relased forks (" + philosopher.getLeftForkIndex() + ", " + philosopher.getRightForkIndex() + ").");
    }

    @Override
    public String getName() {
        return "Rozwiązanie z możliwością zagłodzenia";
    }
}
