import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EnhancedCalibrationScanner {
    static Map<String, Integer> numbers = Map.of(
            "zero", 0,
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
            );
    public static List<String> readFileInList(String fileName)
    {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(
                    Paths.get(fileName),
                    StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static int findFirstValue(String s) {
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(i);
            StringBuilder stringBuilder = new StringBuilder(sub);
            char ch = stringBuilder.charAt(0);

            if (Character.isDigit(ch)) {
                return Character.digit(ch, 10);
            }

            for (String number: numbers.keySet()) {
                if (sub.startsWith(number)) {
                    return numbers.get(number);
                }
            }
        }

        return -1;
    }

    public static int findLastValue(String s) {
        for (int i = s.length(); i >= 0 ; i--) {
            String sub = s.substring(0, i);
            StringBuilder stringBuilder = new StringBuilder(sub);
            char ch = stringBuilder.charAt(i - 1);

            if (Character.isDigit(ch)) {
                return Character.digit(ch, 10);
            }

            for (String number : numbers.keySet()) {
                if (sub.endsWith(number)) {
                    return numbers.get(number);
                }
            }
        }

        return -1;
    }

    public static int calculateCalibrationValue(String s) {
        return findFirstValue(s) * 10 + findLastValue(s);
    }

    public static int calculateCalibrationSum(Reader reader) {
        int sum = 0;

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;

            while ((line = br.readLine()) != null) {
                sum += calculateCalibrationValue(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }
}
