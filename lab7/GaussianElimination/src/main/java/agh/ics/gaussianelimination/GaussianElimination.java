package agh.ics.gaussianelimination;

import agh.ics.gaussianelimination.errors.IndexOutOfRangeException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class GaussianElimination {
    private final Matrix matrix;
    private final int rows;
    private final int cols;
    private final Matrix cResults;
    private final Matrix dResults;

    public GaussianElimination(Matrix matrix) {
        this.matrix = matrix;
        rows = matrix.getRows();
        cols = matrix.getCols();
        cResults = new Matrix(rows);
        dResults = new Matrix(rows);
    }

    private double AOperation(int i) throws IndexOutOfRangeException {
        return  1 / matrix.getValue(i, i);
    }

    private void BOperation(int i, int j, double mi) throws IndexOutOfRangeException {
       matrix.multiplyValue(i, j, mi);
    }

    private void COperation(int i, int k) throws IndexOutOfRangeException {
        cResults.setValue(i, k,matrix.getValue(k, i) /  matrix.getValue(i, i));
    }

    private void DOperation(int i, int j, int k) throws IndexOutOfRangeException {
        dResults.setValue(k, j, matrix.getValue(i, j) * cResults.getValue(i, k));
    }

    private void EOperation(int i, int j, int ignoredK) throws IndexOutOfRangeException {
        matrix.subtractValue(i, j, dResults.getValue(i, j));
    }

    private void startBOperations(int i, double mi) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int j = i; j < cols; j++) {
            final int index = j;
            executor.submit(() -> {
                try {
                    BOperation(i, index, mi);
                } catch (IndexOutOfRangeException e) {
                    System.err.println(e.getMessage());
                    System.exit(1);
                }
            });
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(1, TimeUnit.HOURS);
        if (!finished) {
            executor.shutdownNow();
            System.err.println("Executor terminated unsuccessfully");
            System.exit(1);
        }
    }

    private void startCOperations(int i) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int k = 0; k < rows; k++) {
            if (k == i) continue;

            final int index = k;
            executor.submit(() -> {
                try {
                    COperation(i, index);
                } catch (IndexOutOfRangeException e) {
                    System.err.println(e.getMessage());
                    System.exit(1);
                }
            });
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(1, TimeUnit.HOURS);
        if (!finished) {
            executor.shutdownNow();
            System.err.println("Executor terminated unsuccessfully");
            System.exit(1);
        }
    }

    private void startDOperations(int i) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int k = 0; k < rows; k++) {
            if (k == i) continue;

            final int indexK = k;
            for (int j = i; j < cols; j++) {
                final int indexJ = j;

                executor.submit(() -> {
                    try {
                        DOperation(i, indexJ, indexK);
                    } catch (IndexOutOfRangeException e) {
                        System.err.println(e.getMessage());
                        System.exit(1);
                    }
                });
            }
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(1, TimeUnit.HOURS);
        if (!finished) {
            executor.shutdownNow();
            System.err.println("Executor terminated unsuccessfully");
            System.exit(1);
        }
    }

    private void startEOperations(int i) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int k = 0; k < rows; k++) {
            if (k == i) continue;

            final int indexK = k;
            for (int j = i; j < cols; j++) {
                final int indexJ = j;

                executor.submit(() -> {
                    try {
                        EOperation(indexK, indexJ, i);
                    } catch (IndexOutOfRangeException e) {
                        System.err.println(e.getMessage());
                        System.exit(1);
                    }
                });
            }
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(1, TimeUnit.HOURS);
        if (!finished) {
            executor.shutdownNow();
            System.err.println("Executor terminated unsuccessfully");
            System.exit(1);
        }
    }

    private void startOperations(int i) throws IndexOutOfRangeException, InterruptedException {
        double mi = AOperation(i);
        startBOperations(i, mi);
        startCOperations(i);
        startDOperations(i);
        startEOperations(i);
    }

    public void solve() {
        for (int i=0; i<rows; i++) {
            try {
                startOperations(i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }
}
