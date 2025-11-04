import java.util.ArrayList;

public class Main {
    private static final ArrayList<Thread> threads = new ArrayList<>();

    private static void startProblem(int numberOfPhilosophers, Action action, PhilospherListener listener){
        for(int i = 0; i < numberOfPhilosophers; i++){
            Thread thread = new Thread(new Philosopher(i, numberOfPhilosophers, action, listener));
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
        PhilospherListener listener = new PrintingListener();

        switch (args[0]){
            case "1":
                System.out.println("Naive");
                Action naive = new Naive(forks, isTimeRandom, listener);

                startProblem(numberOfPhilosophers, naive, listener);
                joinThreads(listener);
                break;
            case "2":
                System.out.println("Solution with possible starvation");
                Action solutionWithPossibleStarvation = new SolutionWithPossibleStarvation(forks, isTimeRandom, listener);

                startProblem(numberOfPhilosophers, solutionWithPossibleStarvation, listener);
                joinThreads(listener);
                break;
            case "3":
                System.out.println("Asymetric solution");
                Action asymetricSolution = new AsymetricSolution(forks, isTimeRandom, listener);

                startProblem(numberOfPhilosophers, asymetricSolution, listener);
                joinThreads(listener);
                break;
            case "4":
                System.out.println("Solution with waiter");
                Action waiter = new Waiter(forks, numberOfPhilosophers, isTimeRandom, listener);

                startProblem(numberOfPhilosophers, waiter, listener);
                joinThreads(listener);
                break;
            default:
                System.out.println("Possible args: 1, 2, 3, 4");
                break;
        }
    }
}
