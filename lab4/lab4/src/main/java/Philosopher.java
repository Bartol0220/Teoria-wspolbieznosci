import java.util.ArrayList;
import java.util.Random;

public class Philosopher implements Runnable {
    private final Random rand;
    private final int index;
    private final Action action;
    private final int numberOfPhilosophers;
    private final PhilospherListener listener;
    private boolean ate = true;

    private final ArrayList<Double> waitingTimes = new ArrayList<>();
    static final int MAX_ITERATIONS = 100;

    public Philosopher(int index, int numberOfPhilosophers, Action action, PhilospherListener listener) {
        this.index = index;
        this.rand = new Random();
        this.action = action;
        this.numberOfPhilosophers = numberOfPhilosophers;
        this.listener = listener;
    }

    public int getIndex() {
        return index;
    }

    public int getRightForkIndex(){
        return index;
    }

    public int getLeftForkIndex(){
        return (index - 1 + numberOfPhilosophers) % numberOfPhilosophers;
    }

    private void  think(){
        try {
            if (action.isTimeRandom()){
                Thread.sleep(10 + rand.nextInt(2000));
            } else {
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private  void eat() {
        long startTime = System.nanoTime();
        boolean res = action.startEating(this);
        long endTime = System.nanoTime();
        if (!ate) {
            waitingTimes.add((endTime - startTime)/1_000_000.0 + waitingTimes.removeLast());
        } else {
            waitingTimes.add((endTime - startTime)/1_000_000.0);
        }
        ate = res;

        try {
            if (action.isTimeRandom()){
                Thread.sleep(50 + rand.nextInt(1000));
            } else {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        action.endEating(this);
    }

    @Override
    public void run() {
        boolean cond =  true;
        int cnt = 0;

        while(cond){
            listener.newInformation("Philosopher " + index + " is thinking.");
            think();
            listener.newInformation("Philosopher " + index + " is eating.");
            eat();

            if (ate){
                cnt++;
            }

            listener.newInformation("Philosopher " + index + " cnt: " + cnt);
            if (cnt >= MAX_ITERATIONS) {
                cond = false;
            }
        }
        listener.newImportantInformation("Philosopher " + index + " is full.");

        double averageTime = waitingTimes.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        listener.exportTime(averageTime, index, action);
    }
}
