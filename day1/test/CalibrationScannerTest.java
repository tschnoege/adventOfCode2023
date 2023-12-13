import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;


public class CalibrationScannerTest {
    @Test
    public void scanFirstDigit()
    {
        assertEquals(1, CalibrationScanner.scanFirstDigit("1abc2"));
        assertEquals(2, CalibrationScanner.scanFirstDigit("2abc2"));
        assertEquals(1, CalibrationScanner.scanFirstDigit("a1b2c3"));
    }

    @Test
    public void scanLastDigit()
    {
        assertEquals(2, CalibrationScanner.scanLastDigit("1abc2"));
        assertEquals(3, CalibrationScanner.scanLastDigit("2abc3"));
        assertEquals(3, CalibrationScanner.scanLastDigit("a1b2c3"));
    }

    @Test
    public void scanLine()
    {
        assertEquals(12, CalibrationScanner.calculateValue("1abc2d"));
        assertEquals(23, CalibrationScanner.calculateValue("2abc3"));
        assertEquals(13, CalibrationScanner.calculateValue("a1b2c3"));
    }

    @Test
    public void scanMultipleLines() throws FileNotFoundException {
        StringReader reader = new StringReader("1abc2d\n2abc3\na1b2c3");
        assertEquals(12+23+13, CalibrationScanner.calculateSum(reader));

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());
        FileReader fileReader = new FileReader(file);
        assertEquals(55816, CalibrationScanner.calculateSum(fileReader));
    }
}
