package agh.ics.gaussianelimination.errors;

public class ZeroOnDiagonalException extends RuntimeException {
    public ZeroOnDiagonalException(int row) {
        super("Zero on diagonal at " + row + ", " + row + ".");
    }
}
