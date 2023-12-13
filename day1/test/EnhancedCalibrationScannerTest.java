import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class EnhancedCalibrationScannerTest {
    @Test
    public void firstCharacterIsDigit() {
        assertEquals(1, EnhancedCalibrationScanner.findFirstValue("1bc"));
    }
   @Test
    public void stringStartsWithNumber() {
        assertEquals(1, EnhancedCalibrationScanner.findFirstValue("onebc"));
    }
   @Test
    public void findFirstValue() {
       assertEquals(2, EnhancedCalibrationScanner.findFirstValue("o2nebc"));
       assertEquals(1, EnhancedCalibrationScanner.findFirstValue("dfgone3bc"));
    }

    @Test
    public void lastCharacterIsDigit() {
        assertEquals(5, EnhancedCalibrationScanner.findLastValue("bc5"));
    }

    @Test
    public void stringEndsWithNumber() {
        assertEquals(3, EnhancedCalibrationScanner.findLastValue("bcthree"));
    }

   @Test
    public void findLastValue() {
       assertEquals(2, EnhancedCalibrationScanner.findLastValue("o2nebc"));
       assertEquals(9, EnhancedCalibrationScanner.findLastValue("dfg3ninebc"));
       assertEquals(9, EnhancedCalibrationScanner.findLastValue("two1nine"));
    }

    @Test
    public void calculateCalibrationValue() {
        assertEquals(29, EnhancedCalibrationScanner.calculateCalibrationValue("two1nine"));
        assertEquals(83, EnhancedCalibrationScanner.calculateCalibrationValue("eightwothree"));
        assertEquals(13, EnhancedCalibrationScanner.calculateCalibrationValue("abcone2threexyz"));
        assertEquals(24, EnhancedCalibrationScanner.calculateCalibrationValue("xtwone3four"));
        assertEquals(42, EnhancedCalibrationScanner.calculateCalibrationValue("4nineeightseven2"));
        assertEquals(14, EnhancedCalibrationScanner.calculateCalibrationValue("zoneight234"));
        assertEquals(76, EnhancedCalibrationScanner.calculateCalibrationValue("7pqrstsixteen"));
        assertEquals(21, EnhancedCalibrationScanner.calculateCalibrationValue("twone"));
    }

    @Test
    public void calculateCalibrationSum() throws FileNotFoundException {
        StringReader reader = new StringReader("two1nine\neightwothree\n7pqrstsixteen\ntwone");
        assertEquals(29+83+76+21, EnhancedCalibrationScanner.calculateCalibrationSum(reader));

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());
        FileReader fileReader = new FileReader(file);
        assertEquals(54980, EnhancedCalibrationScanner.calculateCalibrationSum(fileReader));
    }
}