import java.io.*;

public class CalibrationScanner {
   public static int calculateSum(Reader reader) {
        int sum = 0;

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;

            while ((line = br.readLine()) != null) {
                sum += calculateValue(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }

    public static int calculateValue(String input) {
        return scanFirstDigit(input) * 10 + scanLastDigit(input);
    }

    public static int scanFirstDigit(String input) {
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isDigit(ch)) {
                return Character.digit(ch, 10);
            }
        }

        return -1;
    }

    public static int scanLastDigit(String input) {
        for (int i = input.length() - 1; i >= 0; i--) {
            char ch = input.charAt(i);

            if (Character.isDigit(ch)) {
                return Character.digit(ch, 10);
            }
        }

        return -1;
    }
}
