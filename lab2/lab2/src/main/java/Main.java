public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();

        long startTime = System.currentTimeMillis();
        race(counter);
        long endTime = System.currentTimeMillis();

        System.out.println("\nNot synchronized");
        System.out.println("Counter value = " + counter.getValue());
        System.out.println("Race time: " + (endTime - startTime) + "ms");

        counter.restart();

        startTime = System.currentTimeMillis();
        raceSync(counter);
        endTime = System.currentTimeMillis();

        System.out.println("\nSynchronized");
        System.out.println("Counter value = " + counter.getValue());
        System.out.println("Race time: " + (endTime - startTime) + "ms");

        counter.restart();

        startTime = System.currentTimeMillis();
        raceSemafor(counter);
        endTime = System.currentTimeMillis();

        System.out.println("\nSynchronized Semafor");
        System.out.println("Counter value = " + counter.getValue());
        System.out.println("Race time: " + (endTime - startTime) + "ms");

        counter.restart();

        startTime = System.currentTimeMillis();
        raceSemaforLicznikowy(counter);
        endTime = System.currentTimeMillis();

        System.out.println("\nSynchronized Semafor licznikowy");
        System.out.println("Counter value = " + counter.getValue());
        System.out.println("Race time: " + (endTime - startTime) + "ms");
    }

    private static void race(Counter counter) {
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 10e6; i++) {
                counter.increment();
            }
        });

        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 10e6; i++) {
                counter.decrement();
            }
        });

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
            System.exit(1);
        }
    }

    private static void raceSync(Counter counter) {
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 10e6; i++) {
                counter.incementSync();
            }
        });

        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 10e6; i++) {
                counter.decrementSync();
            }
        });

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
            System.exit(1);
        }
    }

    private static void raceSemafor(Counter counter) {
        Semafor semafor = new Semafor();

        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 10e6; i++) {
                try {
                    semafor.P();
                } catch (InterruptedException e) {
                    System.err.println("Interrupted");
                    System.exit(1);
                }
                counter.increment();
                semafor.V();
            }
        });

        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 10e6; i++) {
                try {
                    semafor.P();
                } catch (InterruptedException e) {
                    System.err.println("Interrupted");
                    System.exit(1);
                }
                counter.decrement();
                semafor.V();
            }
        });

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
            System.exit(1);
        }
    }

    private static void raceSemaforLicznikowy(Counter counter) {
        SemaforLicznikowy semafor = new SemaforLicznikowy(1);

        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 10e6; i++) {
                try {
                    semafor.P();
                } catch (InterruptedException e) {
                    System.err.println("Interrupted");
                    System.exit(1);
                }
                counter.increment();
                semafor.V();
            }
        });

        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 10e6; i++) {
                try {
                    semafor.P();
                } catch (InterruptedException e) {
                    System.err.println("Interrupted");
                    System.exit(1);
                }
                counter.decrement();
                semafor.V();
            }
        });

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
            System.exit(1);
        }
    }

}
