import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class UnitSet {

    private List<Unit> units = new ArrayList<Unit>();

    public UnitSet(String input) {
        String[] unitSplit = input.split(", ");

        for (String unitString: unitSplit) {
            Unit unit = new Unit(unitString);
            units.add(unit);
        }
    }

    public UnitSet() {

    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public boolean addUnitsWithGreaterCount(UnitSet unitSet) {
        boolean unitsAdded = false;

        for (Unit unit: unitSet.getUnits()) {
            if (addUnitWithGreaterCount(unit)) {
                unitsAdded = true;
            }
        }

        return unitsAdded;
    }

    public boolean addUnitWithGreaterCount(Unit unitToAdd) {
        boolean colorMatchFound = false;

        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);

            if (unit.getColor() == unitToAdd.getColor())
            {
                colorMatchFound = true;

                if (unitToAdd.getCount() > unit.getCount()) {
                    units.set(i, unitToAdd);
                    return true;
                }
            }
        }

        if (!colorMatchFound)
        {
            units.add(unitToAdd);
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitSet unitSet = (UnitSet) o;
        return Objects.equals(units, unitSet.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(units);
    }

    @Override
    public String toString() {
        String result =  "UnitSet {";

        for (int i = 0; i < units.size(); i++) {
            result += units.get(i);

            if (i < units.size() - 1) {
                result += ", ";
            }
        }

        return result + "}";
    }

    public List<Unit> getUnits() {
        return units;
    }

    public boolean isUnitValid(Unit maxUnit) {
        for (Unit unit: units) {
            if (unit.getColor() == maxUnit.getColor()) {
                if (unit.getCount() > maxUnit.getCount())
                    return false;
            }
        }

        return true;
    }
}
