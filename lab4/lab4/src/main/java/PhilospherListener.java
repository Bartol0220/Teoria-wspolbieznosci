import java.io.FileWriter;
import java.io.IOException;

public interface PhilospherListener {
    void newInformation(String information);

    void newImportantInformation(String information);

    default void exportTime(double averageTime, int philosopherIndex, Action action) {
        String line = action.getName() + "," + philosopherIndex + "," + averageTime + ",";

        try (FileWriter writer = new FileWriter("data.csv", true)) {
            writer.write(line + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
