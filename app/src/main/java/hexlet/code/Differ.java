package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String data1 = fileToString(filepath1);
        String data2 = fileToString(filepath2);

        String type1 = getFileExtension(filepath1);
        String type2 = getFileExtension(filepath2);

        Map<String, Object> config1 = Parser.parse(data1, type1);
        Map<String, Object> config2 = Parser.parse(data2, type2);
        List<Node> nodesList = MapDiffer.getDiff(config1, config2);

        return Formatter.format(nodesList, format).trim();
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String fileToString(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (Files.notExists(path)) {
            throw new Exception("File " + path + " does not exist");
        }

        return Files.readString(path);
    }

    private static String getFileExtension(String filepath) {
        String extension = "";
        int i = filepath.lastIndexOf('.');
        if (i > 0) {
            extension = filepath.substring(i + 1);
        }
        return extension;
    }
}
