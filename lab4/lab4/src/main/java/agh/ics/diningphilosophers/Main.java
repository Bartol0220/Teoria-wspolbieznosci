package agh.ics.diningphilosophers;

import agh.ics.diningphilosophers.model.Forks;
import agh.ics.diningphilosophers.model.Philosopher;
import agh.ics.diningphilosophers.solutions.*;

import java.util.ArrayList;

public class Main {
    private static final ArrayList<Thread> threads = new ArrayList<>();

    private static void startProblem(int numberOfPhilosophers, Solution solution, PhilospherListener listener){
        threads.clear();
        for(int i = 0; i < numberOfPhilosophers; i++){
            Thread thread = new Thread(new Philosopher(i, numberOfPhilosophers, solution, listener));
            threads.add(thread);
            thread.start();
        }
    }

    private static void joinThreads(PhilospherListener listener){
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        listener.newImportantInformation("Threads joined.");
    }

    public static void main(String[] args) {
        int numberOfPhilosophers = 5;
        boolean isTimeRandom = false;
        Forks forks = new Forks(numberOfPhilosophers);
        PhilospherListener listener = new MutedListener();

        switch (args[0]){
            case "1":
                System.out.println("Naive solution");
                Solution naive = new SolutionNaive(forks, isTimeRandom, listener);

                startProblem(numberOfPhilosophers, naive, listener);
                joinThreads(listener);
                break;
            case "2":
                System.out.println("Solution with possible starvation");
                Solution solutionWithPossibleStarvation = new SolutionWithPossibleStarvation(forks, isTimeRandom, listener);

                startProblem(numberOfPhilosophers, solutionWithPossibleStarvation, listener);
                joinThreads(listener);
                break;
            case "3":
                System.out.println("Asymetric solution");
                Solution asymetricSolution = new SolutionAsymetric(forks, isTimeRandom, listener);

                startProblem(numberOfPhilosophers, asymetricSolution, listener);
                joinThreads(listener);
                break;
            case "4":
                System.out.println("Solution with waiter");
                Solution waiter = new SolutionWithWaiter(forks, numberOfPhilosophers, isTimeRandom, listener);

                startProblem(numberOfPhilosophers, waiter, listener);
                joinThreads(listener);
                break;
            case "5":
                int[] numbersOfphilosophers = {5, 10, 15, 30};

                System.out.println("Solutions: solution with possible starvation, asymetric solution, solution with waiter");

                for (int number: numbersOfphilosophers){
                    forks = new Forks(number);
                    System.out.println("Solutions for number of philosophers: " + number);
                    Solution allSolutionWithPossibleStarvation = new SolutionWithPossibleStarvation(forks, isTimeRandom, listener);
                    Solution allAsymetricSolution = new SolutionAsymetric(forks, isTimeRandom, listener);
                    Solution allWaiter = new SolutionWithWaiter(forks, number, isTimeRandom, listener);

                    System.out.println("Solution with possible starvation");
                    startProblem(number, allSolutionWithPossibleStarvation, listener);
                    joinThreads(listener);

                    System.out.println("Asymetric solution");
                    startProblem(number, allAsymetricSolution, listener);
                    joinThreads(listener);

                    System.out.println("Solution with waiter");
                    startProblem(number, allWaiter, listener);
                    joinThreads(listener);
                }
                break;
            default:
                System.out.println("Possible args: 1, 2, 3, 4, 5");
                break;
        }
    }
}
