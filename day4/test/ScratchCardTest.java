import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScratchCardTest {

    @Test
    public void calculatePointsForWinningNumbers() {
        Scratchcard card = new Scratchcard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");
        assertEquals(8, card.calculatePoints());

        card = new Scratchcard("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19");
        assertEquals(2, card.calculatePoints());

        card = new Scratchcard("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1");
        assertEquals(2, card.calculatePoints());

        card = new Scratchcard("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83");
        assertEquals(1, card.calculatePoints());

        card = new Scratchcard("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36");
        assertEquals(0, card.calculatePoints());

        card = new Scratchcard("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");
        assertEquals(0, card.calculatePoints());
    }

    @Test
    public void calculatePoints() throws FileNotFoundException {
        String cards = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
                "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
                "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11";
        Reader reader = new StringReader(cards);
        assertEquals(13.0, Scratchcard.sumAllPoints(reader));

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());
        reader = new FileReader(file);
        assertEquals(23235.0, Scratchcard.sumAllPoints(reader));
    }

    @Test
    public void calculateTotalScratchcards() throws FileNotFoundException {
        String cards = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
                "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
                "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11";
        Reader reader = new StringReader(cards);
        assertEquals(30, Scratchcard.calculateTotalScratchcards(reader));

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());
        reader = new FileReader(file);
        assertEquals(0, Scratchcard.calculateTotalScratchcards(reader));
    }
}
