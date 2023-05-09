package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.LinkedHashMap;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map config1 = deserializeJsonToJsonNode(fileToString(filepath1));
        Map config2 = deserializeJsonToJsonNode(fileToString(filepath2));

        Set<String> keys = new LinkedHashSet(config1.keySet());
        keys.addAll(config2.keySet());
        SortedSet<String> sortedKeys = new TreeSet(keys);

        return stylishOutput(sortedKeys, config1, config2);
    }

    public static String fileToString(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (Files.notExists(path)) {
            throw new Exception("File " + path + " is not exist");
        }

        return Files.readString(path);
    }

    public static Map deserializeJsonToJsonNode(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Map.class);
    }

    public static String stylishOutput(Set<String> keys, Map configMap1, Map configMap2) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> res = new LinkedHashMap();

        for (String key: keys) {
            JsonNode jsonNode1 = objectMapper.valueToTree(configMap1.get(key));
            JsonNode jsonNode2 = objectMapper.valueToTree(configMap2.get(key));

            if (configMap1.containsKey(key) && configMap2.containsKey(key)) {
                if (configMap1.get(key).equals(configMap2.get(key))) {
                    res.put("  " + key, checkDataType(jsonNode1));
                } else if (!configMap1.get(key).equals(configMap2.get(key))) {
                    res.put("- " + key, checkDataType(jsonNode1));
                    res.put("+ " + key, checkDataType(jsonNode2));
                }
            } else if (!configMap1.containsKey(key) && configMap2.containsKey(key)) {
                res.put("+ " + key, checkDataType(jsonNode2));
            } else if (configMap1.containsKey(key) && !configMap2.containsKey(key)) {
                res.put("- " + key, checkDataType(jsonNode1));
            }

        }

        stringBuilder.append("{\n");

        for (Map.Entry<String, String> field: res.entrySet()) {
            stringBuilder.append("  ")
                    .append(field.getKey())
                    .append(": ")
                    .append(field.getValue())
                    .append("\n");
        }

        stringBuilder.append("}");

        return stringBuilder.toString().replace("\"", "");

    }

    public static String checkDataType(JsonNode node) {
        if (node.isObject()) {
            return node.toString();
        } else if (node.isArray()) {
            return node.toString();
        } else {
            return node.toString();
        }
    }
}
