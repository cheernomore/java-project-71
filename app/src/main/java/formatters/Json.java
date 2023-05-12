package formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Json {

    public static String convert(List<Node> nodeList) throws JsonProcessingException {
        Map<String, Map<String, Object>> mapJson = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (Node node: nodeList) {
            String key = node.getKey();
            Object value = node.getValue();
            String state = node.getState();

            switch (state) {
                case "changed":
                    if(!mapJson.containsKey("changed")) {
                        mapJson.put("changed", new HashMap<>());
                    }
                    mapJson.get("changed").put(key, value);
                    break;
                case "added":
                    if (!mapJson.containsKey("added")) {
                        mapJson.put("added", new HashMap<>());
                    }
                    mapJson.get("added").put(key, value);
                    break;
                case "deleted":
                    if (!mapJson.containsKey("deleted")) {
                        mapJson.put("deleted", new HashMap<>());
                    }
                    mapJson.get("deleted").put(key, value);
                    break;
                default:
                    break;
            }
        }

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapJson);
    }
}
