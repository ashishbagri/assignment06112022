package jeff.question1.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Utility to generate CSV
 */
public class CSVGenerator {

    /**
     * Takes given data and output file
     * Use PrintWriter class to output the data.
     * @param data
     * @param fileName
     */
    public static void generateCSV(List<String> data, String fileName){
        File csvOutputFile = new File(fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            data.stream()
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
