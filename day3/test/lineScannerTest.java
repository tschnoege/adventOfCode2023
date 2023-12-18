import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class lineScannerTest {
    @Test
    public void readNumbersFromLine() {
        int sum = 0;
        Cell[] cells = LineScanner.readNumbersFromLine("467..114..");

        for (Cell cell: cells) {
            if (cell != null) {
                sum += cell.number;
            }
        }

        assertEquals(3*467 + 3*114, sum);
    }
   @Test
    public void readSymbolsFromLine() {
        int chars[] = LineScanner.readSymbolsFromLine(".346..$.*..12..");
        assertEquals('$', chars[6]);
        assertEquals(0, chars[7]);
        assertEquals('*', chars[8]);
    }
}
