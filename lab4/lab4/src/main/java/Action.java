public interface Action {
    boolean startEating(Philosopher philosopher);

    void endEating(Philosopher philosopher);

    boolean isTimeRandom();

    String getName();
}
