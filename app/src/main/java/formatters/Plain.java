package formatters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Node;
import java.util.List;

public class Plain {

    public static String output(List<Node> nodeList) {
        StringBuilder stringBuilder = new StringBuilder();


        for (Node node: nodeList) {
            String key = node.getKey();
            Object value = node.getValue();
            String state = node.getState();

            JsonNode jsonNodeValue = toJsonNode(value);
            String preparedValue = checkDataType(jsonNodeValue, "plain");

            switch (state) {
                case "changed":
                    JsonNode jsonNodeOldValue = toJsonNode(node.getOldValue());
                    String preparedOldValue = checkDataType(jsonNodeOldValue, "plain");
                    stringBuilder
                            .append("Property '" + key + "' was updated. ")
                            .append("From " + preparedOldValue + " to " + preparedValue + "\n");
                    break;
                case "added":
                    stringBuilder
                            .append("Property '" + key + "' was added with value: " + preparedValue + "\n");
                    break;
                case "deleted":
                    stringBuilder
                            .append("Property '" + key + "' was removed" + "\n");
                    break;
                default:
                    break;
            }
        }

        return stringBuilder.toString();
    }

    public static String checkDataType(JsonNode node, String format) {

        if (format.equals("plain")) {
            if (node.isObject() || node.isArray()) {
                return "[complex value]";
            } else if (node.isNull() || node.isInt() || node.isBoolean()) {
                return node.toString();
            } else {
                return "'" + clearString(node.toString()) + "'";
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
            return node.toString();
        }
    }

    public static String clearString(String value) {
        return value
                .replace("\"", "")
                .replace(",", ", ")
                .replace(":", "=");
    }

    public static JsonNode toJsonNode(Object value) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.valueToTree(value);
    }
}
