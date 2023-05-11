package hexlet.code;

import java.util.Map;
import java.util.List;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> config1 = Parser.parseFile(filepath1);
        Map<String, Object> config2 = Parser.parseFile(filepath2);
        List<Node> nodesList = MapDiffer.getDiff(config1, config2);

        return Formatter.format(nodesList, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> config1 = Parser.parseFile(filepath1);
        Map<String, Object> config2 = Parser.parseFile(filepath2);
        List<Node> nodesList = MapDiffer.getDiff(config1, config2);

        return Formatter.format(nodesList, "stylish");
    }
}
