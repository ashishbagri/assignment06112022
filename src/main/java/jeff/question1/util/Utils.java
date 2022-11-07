package jeff.question1.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class to create Comma separated data.
 */
public class Utils {
    public static final String COMMA=",";

    public static String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }
}
