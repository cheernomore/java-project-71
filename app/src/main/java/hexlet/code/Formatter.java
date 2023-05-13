package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import formatters.Json;
import formatters.Plain;
import formatters.Stylish;

import java.util.List;

public class Formatter {
    public static String format(List<Node> nodesList, String format) throws JsonProcessingException {
        switch (format) {
            case "plain":
                showOutput(Plain.convert(nodesList));
                return Plain.convert(nodesList);
            case "stylish":
                showOutput(Stylish.convert(nodesList));
                return Stylish.convert(nodesList);
            case "json":
                showOutput(Json.convert(nodesList));
                return Json.convert(nodesList);
            default:
                throw new RuntimeException("Not supported format");
        }
    }

    public static void showOutput(String formattedDiffer) {
        System.out.println(formattedDiffer);
    }
}
