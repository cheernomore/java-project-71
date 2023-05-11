package hexlet.code;

import formatters.Plain;
import formatters.Stylish;

import java.util.List;

public class Formatter {
    public static String format(List<Node> nodesList, String format) {
        switch (format) {
            case "plain":
                return Plain.convert(nodesList);
            case "stylish":
                return Stylish.convert(nodesList);
            default:
                throw new RuntimeException("Not supported format");
        }
    }
}
