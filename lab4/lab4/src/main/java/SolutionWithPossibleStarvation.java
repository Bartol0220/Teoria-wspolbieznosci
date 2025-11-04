public class SolutionWithPossibleStarvation implements Action {
    private final Forks forks;
    private final boolean isTimeRandom;
    private final PhilospherListener listener;

    public SolutionWithPossibleStarvation(Forks forks, boolean isTimeRandom, PhilospherListener listener) {
        this.forks = forks;
        this.isTimeRandom = isTimeRandom;
        this.listener = listener;
    }

    public boolean isTimeRandom() {
        return isTimeRandom;
    }

    @Override
    public boolean startEating(Philosopher philosopher) {
        Fork leftFork = forks.getFork(philosopher.getLeftForkIndex());
        Fork rightFork = forks.getFork(philosopher.getRightForkIndex());

        synchronized (leftFork) {
            if (leftFork.isUsed()) {
                return false;
            } else {
                synchronized (rightFork) {
                    if (rightFork.isUsed()) {
                        return false;
                    }
                    leftFork.useFork();
                    rightFork.useFork();
                    listener.newInformation("Philosopher " + philosopher.getIndex() + " picked up forks (" + philosopher.getLeftForkIndex() + ", " + philosopher.getRightForkIndex() + ").");
                }
            }
        }
        return true;
    }

    @Override
    public void endEating(Philosopher philosopher) {
        Fork leftFork = forks.getFork(philosopher.getLeftForkIndex());
        Fork rightFork = forks.getFork(philosopher.getRightForkIndex());
        synchronized (leftFork) {
            synchronized (rightFork) {
                leftFork.relaseFork();
                rightFork.relaseFork();
                listener.newInformation("Philosopher " + philosopher.getIndex() + " relased forks (" + philosopher.getLeftForkIndex() + ", " + philosopher.getRightForkIndex() + ").");
            }
        }
    }

    @Override
    public String getName() {
        return "Rozwiązanie z możliwością zagłodzenia";
    }
}
