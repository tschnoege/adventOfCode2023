import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class EngineSchematic {
    public static int checkSumForParts(Reader reader) {
        List<Cell[]> cellMatrix = new ArrayList<>();
        List<int[]> charMatrix = new ArrayList<>();

        fillMatrixes(reader, cellMatrix, charMatrix);

        int sum = 0;
        Set<Cell> cellSet = new HashSet<>();

        for (int i = 0; i < cellMatrix.size(); i++) {
            Cell[] cells = cellMatrix.get(i);

            cellSet.addAll(getValidPartsForIndex(i, cells, charMatrix));
        }

        for (Cell cell: cellSet) {
            sum += cell.number;
        }

        return sum;
    }
    public static int gearRatio(Reader reader) {
        List<Cell[]> cellMatrix = new ArrayList<>();
        List<int[]> charMatrix = new ArrayList<>();

        fillMatrixes(reader, cellMatrix, charMatrix);

        int sum = 0;

        for (int i = 0; i < charMatrix.size(); i++) {
            int[] chars = charMatrix.get(i);

            sum += getGearRatioForIndex(i, chars, cellMatrix);
        }

        return sum;
    }

    private static int getGearRatioForIndex(int lineIndex, int[] chars, List<Cell[]> cellMatrix) {
        int sum = 0;

        for (int charIndex = 0; charIndex < chars.length; charIndex++) {
            if (chars[charIndex] == '*') {
                Set<Cell> cellSet = new HashSet<>();

                if (lineIndex - 1 >= 0) {
                    cellSet.addAll(getGearRation(charIndex, cellMatrix.get(lineIndex - 1)));
                }

                cellSet.addAll(getGearRation(charIndex, cellMatrix.get(lineIndex)));

                if (lineIndex + 1 < cellMatrix.size()) {
                    cellSet.addAll(getGearRation(charIndex, cellMatrix.get(lineIndex + 1)));
                }

                if (cellSet.size() >= 2) {
                    int gearRation = 1;

                    for (Cell cell : cellSet) {
                        gearRation *= cell.number;
                    }

                    sum += gearRation;
                }
            }
        }

        return sum;
    }

    private static Set<Cell> getGearRation(int charIndex, Cell[] cells) {
        Set<Cell> cellSet = new HashSet<>();
        int length = cells.length;

        if (charIndex > 0 && cells[charIndex - 1] != null) {
            cellSet.add(cells[charIndex - 1]);
        }

        if (cells[charIndex] != null)
            cellSet.add(cells[charIndex]);

        if (charIndex + 1 < length && cells[charIndex + 1] != null)
            cellSet.add(cells[charIndex + 1]);

        return cellSet;
    }

    private static void fillMatrixes(Reader reader, List<Cell[]> cellMatrix, List<int[]> charMatrix) {
        try (BufferedReader br = new BufferedReader(reader)) {
            String line;

            while ((line = br.readLine()) != null) {
                Cell[] cells = LineScanner.readNumbersFromLine(line);
                cellMatrix.add(cells);

                int[] chars = LineScanner.readSymbolsFromLine(line);
                charMatrix.add(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Set<Cell> getValidPartsForIndex(int index, Cell[] cells, List<int[]> charMatrix) {
        Set<Cell> cellSet = new HashSet<>();

        if (index - 1 >= 0) {
            cellSet.addAll(getValidParts(cells, charMatrix.get(index - 1)));
        }

        cellSet.addAll(getValidParts(cells, charMatrix.get(index)));

        if (index + 1 < charMatrix.size()) {
            cellSet.addAll(getValidParts(cells, charMatrix.get(index + 1)));
        }

        return cellSet;
    }

    private static Set<Cell> getValidParts(Cell[] cells, int[] chars) {
        Set<Cell> cellSet = new HashSet<>();
        int length = cells.length;

        for (int j = 0; j < length; j++) {
            if (cells[j] != null) {
                boolean isPartNumber =
                        (j > 0 && chars[j - 1] > 0) ||
                        (chars[j] > 0) ||
                        (j < length - 1 && chars[j + 1] > 0);

                if (isPartNumber) {
                    cellSet.add(cells[j]);
                }
            }
        }

        return cellSet;
    }
}
