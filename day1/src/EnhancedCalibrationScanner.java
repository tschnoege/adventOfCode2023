import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnhancedCalibrationScanner {
    public static List<String> readFileInList(String fileName)
    {
        List<String> lines;
        try {
            lines = Files.readAllLines(
                    Paths.get(fileName),
                    StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            List<String> exceptionList = new ArrayList<>();
            exceptionList.add(FILE_NOT_FOUND);
            lines = exceptionList;
        }
        return lines;
    }
}
