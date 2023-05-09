package formatters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Node;

import java.util.List;

public class Stylish {

    public static String output(List<Node> nodeList) {
        StringBuilder stringBuilder = new StringBuilder();


        for (Node node: nodeList) {
            String key = node.getKey();
            Object value = node.getValue();
            String state = node.getState();

            JsonNode jsonNodeValue = toJsonNode(value);
            String preparedValue = checkDataType(jsonNodeValue);

            stringBuilder.append("  ");

            switch (state) {
                case "unchanged":
                    stringBuilder
                            .append("  ")
                            .append(key + ": ")
                            .append(preparedValue)
                            .append("\n");
                    break;
                case "changed":
                    JsonNode jsonNodeOldValue = toJsonNode(node.getOldValue());
                    String preparedOldValue = checkDataType(jsonNodeOldValue);
                    stringBuilder
                            .append("- ")
                            .append(key + ": ")
                            .append(preparedOldValue + "\n")
                            .append("  ")
                            .append("+ ")
                            .append(key + ": ")
                            .append(preparedValue + "\n");
                    break;
                case "added":
                    stringBuilder
                            .append("+ ")
                            .append(key + ": ")
                            .append(preparedValue)
                            .append("\n");
                    break;
                case "deleted":
                    stringBuilder
                            .append("- ")
                            .append(key + ": ")
                            .append(preparedValue)
                            .append("\n");
                    break;
                default:
                    break;
            }
        }

        return stringBuilder.insert(0, "{\n").append("}").toString();
    }

    public static String checkDataType(JsonNode node, String format) {

        if (format.equals("plain")) {
            if (node.isObject() || node.isArray()) {
                return "[complex value]";
            } else if (node.isNull() || node.isInt() || node.isBoolean()) {
                return node.toString();
            } else {
                return "'" + node.toString() + "'";
            }
        }

        if (node.isObject()) {
            return clearString(node.toString());
        } else if (node.isArray()) {
            return clearString(node.toString());
        } else {
            return node.toString();
        }
    }

    public static String checkDataType(JsonNode node) {
        if (node.isObject()) {
            return clearString(node.toString());
        } else if (node.isArray()) {
            return clearString(node.toString());
        } else {
            return clearString(node.toString());
        }
    }

    public static String clearString(String value) {
        return value
                .replace(",", ", ")
                .replace("\"", "")
                .replace(":", "=");
    }

    public static JsonNode toJsonNode(Object value) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.valueToTree(value);
    }
}
