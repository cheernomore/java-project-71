package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {
    public static String format(List<Node> nodesList, String format) throws JsonProcessingException {
        switch (format) {
            case "plain":
                return Plain.convert(nodesList);
            case "stylish":
                return Stylish.convert(nodesList);
            case "json":
                return Json.convert(nodesList);
            default:
                throw new RuntimeException("Not supported format");
        }
    }
}
