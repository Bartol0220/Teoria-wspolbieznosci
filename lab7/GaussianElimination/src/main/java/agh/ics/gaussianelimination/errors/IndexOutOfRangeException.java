package agh.ics.gaussianelimination.errors;

public class IndexOutOfRangeException extends Exception {
    public IndexOutOfRangeException(int row, int col, int rows, int cols) {
        super("Index out of range. Size of matrix: " + rows + "x" + cols + ". Attempt to refer to the index: " + row + ", " + col + ".");
    }
}
