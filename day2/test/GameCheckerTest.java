import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameCheckerTest {
//    Example:
//    Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
//    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
//    Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
//    Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
//    Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green

    @Test
    public void ReadUnitCountAndColor() {
        Unit unit = new Unit("3 blue");
        assertEquals(3, unit.getCount());
        assertEquals(ColorEnum.blue, unit.getColor());

        unit = new Unit("20 red");
        assertEquals(20, unit.getCount());
        assertEquals(ColorEnum.red, unit.getColor());
    }

    @Test
    public void readUnits() {
        List<Unit> unitsInSet = GameChecker.readUnitsFromSet("3 blue, 4 red");

        assertEquals(new Unit(ColorEnum.blue, 3), unitsInSet.get(0));
        assertEquals(new Unit(ColorEnum.red, 4), unitsInSet.get(1));

        unitsInSet = GameChecker.readUnitsFromSet("8 green, 6 blue, 20 red");

        assertEquals(new Unit(ColorEnum.green, 8), unitsInSet.get(0));
        assertEquals(new Unit(ColorEnum.blue, 6), unitsInSet.get(1));
        assertEquals(new Unit(ColorEnum.red, 20), unitsInSet.get(2));
    }

    @Test
    public void readGame() {
        String input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        Game game = new Game();
        game.setIdentifier("Game 1");

        UnitSet unitSet = new UnitSet();
        unitSet.addUnit(new Unit(ColorEnum.blue, 3));
        unitSet.addUnit(new Unit(ColorEnum.red, 4));
        game.addUnitSet(unitSet);

        unitSet = new UnitSet();
        unitSet.addUnit(new Unit(ColorEnum.red, 1));
        unitSet.addUnit(new Unit(ColorEnum.green, 2));
        unitSet.addUnit(new Unit(ColorEnum.blue, 6));
        game.addUnitSet(unitSet);

        unitSet = new UnitSet();
        unitSet.addUnit(new Unit(ColorEnum.green, 2));
        game.addUnitSet(unitSet);

        assertEquals(game, new Game(input));
    }

    @Test
    public void sumPossibleGames() throws FileNotFoundException {
        String input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
        Reader reader = new StringReader(input);
        UnitSet configuration = new UnitSet();
        configuration.addUnit(new Unit(ColorEnum.red, 12));
        configuration.addUnit(new Unit(ColorEnum.green, 13));
        configuration.addUnit(new Unit(ColorEnum.blue, 14));

        assertEquals(8, GameChecker.checkGames(reader, configuration));

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());
        reader = new FileReader(file);

        assertEquals(0, GameChecker.checkGames(reader, configuration));
    }

    @Test
    public void calculatePowerOfMaxUnitSet() {
        Game game = new Game("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        assertEquals(48, game.calculatePowerOfMaxUnitSet());

        game = new Game("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue");
        assertEquals(12, game.calculatePowerOfMaxUnitSet());

        game = new Game("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
        assertEquals(1560, game.calculatePowerOfMaxUnitSet());

        game = new Game("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");
        assertEquals(630, game.calculatePowerOfMaxUnitSet());

        game = new Game("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
        assertEquals(36, game.calculatePowerOfMaxUnitSet());
    }

    @Test
    public void calculatePowerSum() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());
        Reader reader = new FileReader(file);

        assertEquals(72970, GameChecker.calculatePowerSum(reader));
    }

}
