import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
    private String identifier;
    private List<UnitSet> unitSets = new ArrayList<UnitSet>();

    public Game() {
        identifier = null;
    }

    public Game(String gameString) {
        String[] inputSplit = gameString.split(": ");
        identifier = inputSplit[0];

        String[] unitSetStrings = inputSplit[1].split("; ");

        for (String unitSetString : unitSetStrings) {
            UnitSet unitSet = new UnitSet(unitSetString);
            unitSets.add(unitSet);
        }
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void addUnitSet(UnitSet unitSet) {
        unitSets.add(unitSet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(identifier, game.identifier) && Objects.equals(unitSets, game.unitSets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, unitSets);
    }

    @Override
    public String toString() {
        String result = identifier + "[";

        for (int i = 0; i < unitSets.size(); i++) {
            result += unitSets.get(i);

            if (i < unitSets.size() - 1)
                result += ", ";
        }

        return result + "]";
    }

    public int getNumber() {
        if (identifier == null) {
            return 0;
        }

        String[] words = identifier.split(" ");

        if (words.length == 2)
        {
            return Integer.valueOf(words[1]);
        }

        return 0;
    }

    public boolean isValid(UnitSet bagSet) {
        for (Unit unit: bagSet.getUnits()) {
            for (UnitSet set: unitSets) {
                if (set.isUnitValid(unit) == false)
                    return false;
            }
        }

        return true;
    }

    public int calculatePowerOfMaxUnitSet() {
        UnitSet maxSet = new UnitSet();
        int power = 1;

        for (UnitSet unitSet: unitSets) {
            maxSet.addUnitsWithGreaterCount(unitSet);
        }

        for (Unit unit: maxSet.getUnits()) {
            power *= unit.getCount();
        }

        return power;
    }
}
