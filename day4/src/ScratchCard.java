import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ScratchCard {
    private List<Integer>  winningNumbers = null;
    private List<Integer>  myNumbers = null;

    public boolean isWinningNumber(int number) {
        return winningNumbers.contains(number);
    }

    public void readWinningNumbersFromString(String winningNumbersString) {
        winningNumbers = readNumbersFromString(winningNumbersString);
    }

    public void readMyNumbersFromString(String myNumbersString) {
        myNumbers = readNumbersFromString(myNumbersString);
    }

    private static List<Integer>  readNumbersFromString(String numbersString) {
        String[] words = numbersString.split(" ");
        List<Integer> numbers = new ArrayList<>();

        for (String word: words) {
            if (!word.isEmpty()) {
                numbers.add(Integer.valueOf(word));
            }
        }

        return numbers;
    }

    public double calculatePoints() {
        long totalCount = myNumbers.stream()
                .mapToLong(item -> winningNumbers.stream().filter(item::equals).count())
                .sum();

        return (totalCount == 0) ? 0.0 : Math.pow(2, totalCount - 1);
    }
    public static double sumAllPoints(Reader reader) {
        double sum = 0;

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            ScratchCard card = new ScratchCard();

            while ((line = br.readLine()) != null) {
                String[] cardLine = line.split(": ");
                String[] numbers = cardLine[1].split("\\|");

                card.readWinningNumbersFromString(numbers[0]);
                card.readMyNumbersFromString(numbers[1]);

                sum += card.calculatePoints();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }
}
