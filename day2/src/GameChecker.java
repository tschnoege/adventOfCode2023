import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class GameChecker {
    public static List<Unit> readUnitsFromSet(String s) {
        List<Unit> units = new ArrayList<>();
        String[] unitStrings = s.split(", ");

        for (String unitString : unitStrings) {
            units.add(new Unit(unitString));
        }

        return units;
    }

    public static int checkGames(Reader reader, UnitSet bagSet) {
        int sum = 0;

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;

            while ((line = br.readLine()) != null) {
                Game game = new Game(line);

                if (game.isValid(bagSet)) {
                    sum += game.getNumber();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }

    public static int calculatePowerSum(Reader reader) {
        int sum = 0;

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;

            while ((line = br.readLine()) != null) {
                Game game = new Game(line);
                int power = game.calculatePowerOfMaxUnitSet();

                sum += power;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }
}
