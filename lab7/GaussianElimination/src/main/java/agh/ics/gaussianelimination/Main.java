package agh.ics.gaussianelimination;

import agh.ics.gaussianelimination.errors.InvalidFileFormatException;
import agh.ics.gaussianelimination.errors.ZeroOnDiagonalException;

import java.io.FileNotFoundException;

public class Main {
    static void main(String[] args) {
        String fileName = (args.length > 0) ? args[0] : "src/main/resources/input_example1.txt";

        FileManager fileManager = new FileManager();

        try {
            System.out.println("Loading file: " + fileName);
            Matrix matrix = fileManager.parseNewFile(fileName);
            System.out.println("Running gaussian elimination for matrix with size: " + matrix.getRows());

            GaussianElimination elim = new GaussianElimination(matrix);
            elim.solve();

            System.out.println("Done elimination");

            String outputFileName = fileManager.generateOutputFileName(fileName);
            fileManager.saveMatrix(outputFileName, matrix);
            System.out.println("Saved output to: " + outputFileName);
        } catch (FileNotFoundException e) {
            System.err.println("File " + fileName + " not found.");
        } catch (InvalidFileFormatException | ZeroOnDiagonalException e) {
            System.err.println(e.getMessage());
        }
    }
}
