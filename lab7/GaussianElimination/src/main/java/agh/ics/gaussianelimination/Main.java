package agh.ics.gaussianelimination;

import agh.ics.gaussianelimination.errors.InvalidFileFormatException;

import java.io.FileNotFoundException;

public class Main {
    static void main(String[] args) {
        String fileName = (args.length > 0) ? args[0] : "src/main/resources/input_example1.txt";

        FileManager fileManager = new FileManager();

        try {
            Matrix matrix = fileManager.parseNewFile(fileName);
            System.out.println(matrix);

            GaussianElimination elim = new GaussianElimination(matrix);
            elim.solve();

            System.out.println(matrix);

            String outputFileName = fileManager.generateOutputFileName(fileName);
            fileManager.saveMatrix(outputFileName, matrix);
        } catch (FileNotFoundException e) {
            System.err.println("File " + fileName + " not found.");
        } catch (InvalidFileFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}
