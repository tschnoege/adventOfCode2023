public class LineScanner {
    public static Cell[] readNumbersFromLine(String line) {
        Cell[] cells = new Cell[line.length()];
        Cell cell = null;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            if (Character.isDigit(ch)) {
                int digit = Character.digit(ch, 10);

                if (cell == null) {
                    cell = new Cell();
                }

                cell.number = cell.number * 10 + digit;
            } else {
                cell = null;
            }

            cells[i] = cell;
        }

        return cells;
    }

    public static int[] readSymbolsFromLine(String line) {
        int[] chars = new int[line.length()];

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            if (ch != '.' && !Character.isDigit(ch)) {
                chars[i] = ch;
            }
            else {
                chars[i] = 0;
            }
        }

        return chars;
    }
}
