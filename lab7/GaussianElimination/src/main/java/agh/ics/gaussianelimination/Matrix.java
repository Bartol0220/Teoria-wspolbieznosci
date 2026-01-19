package agh.ics.gaussianelimination;

import agh.ics.gaussianelimination.errors.IndexOutOfRangeException;

public class Matrix {
    private final int cols;
    private final int rows;
    private final double[] matrix;

    public Matrix(int size) {
        if (size <= 0) throw new IllegalArgumentException();
        rows = size;
        cols = size + 1;
        matrix = new double[rows*cols];
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    private int getIndex(int row, int col) throws IndexOutOfRangeException {
        if(row<0 || row>=rows || col<0 || col>=cols){
            throw new IndexOutOfRangeException(row, col, rows, cols);
        }
        return row*cols + col;
    }

    public void setValue(int row, int col, double value) throws IndexOutOfRangeException {
        int index = getIndex(row, col);
        matrix[index] = value;
    }

    public void multiplyValue(int row, int col, double value) throws IndexOutOfRangeException {
        int index = getIndex(row, col);
        matrix[index] *= value;
    }

    public void subtractValue(int row, int col, double value) throws IndexOutOfRangeException {
        int index = getIndex(row, col);
        matrix[index] -= value;
    }

    public double getValue(int row, int col) throws IndexOutOfRangeException {
        int index = getIndex(row, col);
        return matrix[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(rows).append("\n");

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols - 1; c++) {
                try {
                    sb.append(getValue(r, c)).append(" ");
                } catch (IndexOutOfRangeException e) {
                    return "";
                }
            }
            sb.append("\n");
        }

        for (int r = 0; r < rows; r++) {
            try {
                sb.append(getValue(r, cols - 1)).append(" ");
            } catch (IndexOutOfRangeException e) {
                return "";
            }
        }
        sb.append("\n");

        return sb.toString();
    }
}
