package formatters;

import hexlet.code.Node;
import java.util.List;

public class Stylish {

    public static String convert(List<Node> nodeList) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Node node: nodeList) {
            String key = node.getKey();
            Object value = node.getValue();
            String state = node.getState();

            String preparedValue = stringify(value);

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
                    String preparedOldValue = stringify(node.getOldValue());
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

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }

        return value.toString();
    }
}
