package hexlet.code;

import formatters.Plain;
import formatters.Stylish;

import java.util.List;

public class Formatter {
    public static String format(List<Node> nodesList, String format) {
        switch (format) {
            case "plain":
                return Plain.output(nodesList);
            default:
                return Stylish.output(nodesList);
        }
    }
}
