package formatters;

import hexlet.code.Node;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String convert(List<Node> nodeList) {
        StringBuilder stringBuilder = new StringBuilder();


        for (Node node: nodeList) {
            String key = node.getKey();
            Object value = node.getValue();
            String state = node.getState();

            String preparedValue = stringify(value);

            switch (state) {
                case "changed":
                    String preparedOldValue = stringify(node.getOldValue());

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

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }

        return value.toString();
    }
}
