import java.util.Map;
import java.util.Objects;

public class Unit {
    ColorEnum color = null;
    int count = 0;

    public Unit(String s) {
        String[] words = s.split(" ");
        this.color = ColorEnum.valueOf(words[1]);
        this.count = Integer.valueOf(words[0]);
    }

    public Unit(ColorEnum color, int count) {
        this.color = color;
        this.count = count;
    }

    public ColorEnum getColor() {
        return color;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return count == unit.count && color == unit.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, count);
    }

    @Override
    public String toString() {
        return "Unit [" + getCount() + " " + getColor() + "]";
    }
}
