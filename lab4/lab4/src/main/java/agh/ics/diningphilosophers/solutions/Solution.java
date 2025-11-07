package agh.ics.diningphilosophers.solutions;
import agh.ics.diningphilosophers.model.Philosopher;

public interface Solution {
    boolean startEating(Philosopher philosopher);

    void endEating(Philosopher philosopher);

    boolean isTimeRandom();

    String getName();
}
