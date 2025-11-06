package agh.ics.diningphilosophers;

import agh.ics.diningphilosophers.solutions.Solution;

import java.io.FileWriter;
import java.io.IOException;

public interface PhilospherListener {
    void newInformation(String information);

    void newImportantInformation(String information);

    default void exportTimeAvg(double averageTime, int philosopherIndex, Solution solution, int numberOfPhilosophers) {
        String line = solution.getName() + "," + numberOfPhilosophers + "," + philosopherIndex + "," + averageTime + "\n";

        try (FileWriter writer = new FileWriter("data.csv", true)) {
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default void exportTimeMax(double maxTime, int philosopherIndex, Solution solution, int numberOfPhilosophers) {
        String line = solution.getName() + "," + numberOfPhilosophers + "," + philosopherIndex + "," + maxTime + "\n";

        try (FileWriter writer = new FileWriter("data2.csv", true)) {
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
