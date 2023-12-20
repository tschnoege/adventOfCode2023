import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scratchcard {
    private int numCopies = 0;
    private long countMatchingNumbers = -1;
    private List<Integer>  winningNumbers = null;
    private List<Integer>  myNumbers = null;

    public Scratchcard() {

    }

    public Scratchcard(String line) {
        String[] cardLine = line.split(": ");
        String[] numbers = cardLine[1].split("\\|");

        readWinningNumbersFromString(numbers[0]);
        readMyNumbersFromString(numbers[1]);
    }

    private void readWinningNumbersFromString(String winningNumbersString) {
        winningNumbers = readNumbersFromString(winningNumbersString);
    }

    private void readMyNumbersFromString(String myNumbersString) {
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

    private long countMatchingNumbers() {
        if (countMatchingNumbers == -1) {
            countMatchingNumbers = myNumbers.stream()
                    .mapToLong(item -> winningNumbers.stream().filter(item::equals).count())
                    .sum();
        }

        return countMatchingNumbers;
    }

    public double calculatePoints() {
        countMatchingNumbers();
        return (countMatchingNumbers == 0) ? 0.0 : Math.pow(2, countMatchingNumbers - 1);
    }

    public static double sumAllPoints(Reader reader) {
        double sum = 0;
        List<Scratchcard> cardList = readScratchcards(reader);

        for (Scratchcard card: cardList) {
            sum += card.calculatePoints();
        }

        return sum;
    }

    public static long calculateTotalScratchcards(Reader reader) {
        List<Scratchcard> cardList = readScratchcards(reader);
        int[] numCopies = new int[cardList.size()];

        for (int i = 0; i < cardList.size(); i++) {
            Scratchcard card = cardList.get(i);
            long count = card.countMatchingNumbers();

            for (int offset = 1; offset <= count; offset++) {
                numCopies[i + offset] += numCopies[i] + 1;
            }
        }

        return Arrays.stream(numCopies).sum() + cardList.size();
    }

    private static List<Scratchcard> readScratchcards(Reader reader) {
        List<Scratchcard> cardList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;

            while ((line = br.readLine()) != null) {
                Scratchcard card = new Scratchcard(line);
                cardList.add(card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cardList;
    }
}
