package agh.ics.gaussianelimination.errors;

public class InvalidFileFormatException extends Exception {
    public InvalidFileFormatException(String fileName) {
        super("File " + fileName + " is not a valid file format.");
    }
}
