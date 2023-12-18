import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngineSchematicTest {
/* example engine schematic
467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
 */
@Test
public void checkSumForParts() throws FileNotFoundException {
    String engineSchematicMap = "467..114..\n" +
            "...*......\n" +
            "..35..633.\n" +
            "......#...\n" +
            "617*......\n" +
            ".....+.58.\n" +
            "..592.....\n" +
            "......755.\n" +
            "...$.*....\n" +
            ".664.598..";
    Reader reader = new StringReader(engineSchematicMap);
    assertEquals(4361, EngineSchematic.checkSumForParts(reader));

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("input.txt").getFile());
    reader = new FileReader(file);
    assertEquals(527144, EngineSchematic.checkSumForParts(reader));
}
    @Test
    public void gearRatio() throws FileNotFoundException {
        String engineSchematicMap = "467..114..\n" +
                "...*......\n" +
                "..35..633.\n" +
                "......#...\n" +
                "617*......\n" +
                ".....+.58.\n" +
                "..592.....\n" +
                "......755.\n" +
                "...$.*....\n" +
                ".664.598..";
        Reader reader = new StringReader(engineSchematicMap);
        assertEquals(467835, EngineSchematic.gearRatio(reader));

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());
        reader = new FileReader(file);
        assertEquals(81463996, EngineSchematic.gearRatio(reader));
    }
}
