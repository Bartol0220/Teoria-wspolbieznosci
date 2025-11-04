import java.util.concurrent.atomic.AtomicInteger;

public class Waiter implements Action {
    private final Forks forks;
    private final boolean isTimeRandom;
    private final PhilospherListener listener;
    private final int maximumNumberOfEatingPhilosophers;
    private final AtomicInteger numberOfEatingPhilosophers = new AtomicInteger(0);

    public Waiter(Forks forks, int numberOfPhilosophers, boolean isTimeRandom, PhilospherListener listener) {
        this.forks = forks;
        this.isTimeRandom = isTimeRandom;
        this.listener = listener;
        this.maximumNumberOfEatingPhilosophers = numberOfPhilosophers - 1;
    }

    public boolean isTimeRandom() {
        return isTimeRandom;
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

            synchronized (leftFork) {
                while (leftFork.isUsed()) {
                    try {
                        leftFork.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                leftFork.useFork();
                listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up left fork (" + philosopher.getLeftForkIndex() + ").");
            }

            synchronized (rightFork) {
                while (rightFork.isUsed()) {
                    try {
                        rightFork.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                rightFork.useFork();
                listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up right fork (" + philosopher.getRightForkIndex() + ").");
            }
        }
        return true;
    }

    @Override
    public void endEating(Philosopher philosopher) {
        Fork leftFork = forks.getFork(philosopher.getLeftForkIndex());
        synchronized (leftFork){
            leftFork.relaseFork();
            leftFork.notifyAll();
            listener.newInformation("Philosopher " + philosopher.getIndex() + " relased left fork (" + philosopher.getLeftForkIndex() + ").");
        }

        Fork rightFork = forks.getFork(philosopher.getRightForkIndex());
        synchronized (rightFork){
            rightFork.relaseFork();
            rightFork.notifyAll();
            listener.newInformation("Philosopher " + philosopher.getIndex() + " relased right fork (" + philosopher.getRightForkIndex() + ").");
        }

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
