package agh.ics.diningphilosophers.solutions;

import agh.ics.diningphilosophers.PhilospherListener;
import agh.ics.diningphilosophers.model.Fork;
import agh.ics.diningphilosophers.model.Forks;
import agh.ics.diningphilosophers.model.Philosopher;

public class SolutionAsymetric extends AbstractSolution {

    public SolutionAsymetric(Forks forks, boolean isTimeRandom, PhilospherListener listener) {
        super(forks, isTimeRandom, listener);
    }

    @Override
    public boolean startEating(Philosopher philosopher) {
        Fork leftFork = forks.getFork(philosopher.getLeftForkIndex());
        Fork rightFork = forks.getFork(philosopher.getRightForkIndex());

        if(philosopher.getIndex() % 2 == 0){
            rightFork.useFork();
            listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up right fork (" + philosopher.getRightForkIndex() + ").");

            leftFork.useFork();
            listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up left fork (" + philosopher.getLeftForkIndex() + ").");
        } else {
            leftFork.useFork();
            listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up left fork (" + philosopher.getLeftForkIndex() + ").");

            rightFork.useFork();
            listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up right fork (" + philosopher.getRightForkIndex() + ").");
        }
        return true;
    }

    @Override
    public String getName() {
        return "Rozwiązanie asymetryczne";
    }
}

