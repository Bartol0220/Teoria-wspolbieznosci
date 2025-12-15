package agh.ics.gaussianelimination;

import agh.ics.gaussianelimination.errors.IndexOutOfRangeException;
import agh.ics.gaussianelimination.errors.InvalidFileFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;


public class FileManager {
    public Matrix  parseNewFile(String fileName) throws InvalidFileFormatException, FileNotFoundException {
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file).useLocale(Locale.US)) {

            if (!scanner.hasNextInt()) {
                throw new InvalidFileFormatException(fileName);
            }
            int n = scanner.nextInt();

            Matrix matrix = new Matrix(n);

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (scanner.hasNextDouble()) {
                        double value = scanner.nextDouble();
                        matrix.setValue(row, col, value);
                    } else {
                        throw new InvalidFileFormatException(fileName);
                    }
                }
            }

            for (int row = 0; row < n; row++) {
                if (scanner.hasNextDouble()) {
                    double vectorValue = scanner.nextDouble();
                    matrix.setValue(row, n, vectorValue);
                } else {
                    throw new InvalidFileFormatException(fileName);
                }
            }

            return matrix;
        } catch (IndexOutOfRangeException e) {
            throw new InvalidFileFormatException(fileName);
        }
    }

    public void saveMatrix(String fileName, Matrix matrix) {
        try {
            Files.writeString(Path.of(fileName), matrix.toString());
        } catch (IOException e) {
            System.err.println("Error writing matrix.");
        }
    }

    public String generateOutputFileName(String fileName) {
        if (fileName == null) return null;

        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex != -1) {
            String namePart = fileName.substring(0, dotIndex);
            String extensionPart = fileName.substring(dotIndex);

            return namePart + "_out" + extensionPart;
        } else {
            return fileName + "_out";
        }
    }
}
