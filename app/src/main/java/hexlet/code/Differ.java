package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map config1 = new HashMap<>();
        Map config2 = new HashMap<>();
        List<Node> nodesList = new LinkedList<>();

        if (filepath1.endsWith(".json") && filepath2.endsWith((".json"))) {
            config1 = deserializeJsonToMap(fileToString(filepath1));
            config2 = deserializeJsonToMap(fileToString(filepath2));
        } else if (filepath1.endsWith(".yaml") && filepath2.endsWith((".yaml"))) {
            config1 = deserializeYamlToMap(fileToString(filepath1));
            config2 = deserializeYamlToMap(fileToString(filepath2));
        }

        Set<String> keys = new LinkedHashSet(config1.keySet());
        keys.addAll(config2.keySet());
        SortedSet<String> sortedKeys = new TreeSet(keys);

        for (String key: sortedKeys) {
            Object value1 = config1.get(key);
            Object value2 = config2.get(key);

            if (value1 == null) {
                value1 = "null";
            }

            if (value2 == null) {
                value2 = "null";
            }

            if (config1.containsKey(key) && config2.containsKey(key)) {
                if (value1.equals(value2)) {
                    nodesList.add(new Node(key, config1.get(key), "unchanged"));
                } else if (!value1.equals(value2)) {
                    nodesList.add(new Node(key, config2.get(key), "changed", config1.get(key)));
                }
            } else if (!config1.containsKey(key) && config2.containsKey(key)) {
                nodesList.add(new Node(key, config2.get(key), "added"));
            } else if (config1.containsKey(key) && !config2.containsKey(key)) {
                nodesList.add(new Node(key, config1.get(key), "deleted"));
            }
        }

        return Formatter.format(nodesList, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        Map config1 = new HashMap<>();
        Map config2 = new HashMap<>();
        List<Node> nodesList = new LinkedList<>();

        if (filepath1.endsWith(".json") && filepath2.endsWith((".json"))) {
            config1 = deserializeJsonToMap(fileToString(filepath1));
            config2 = deserializeJsonToMap(fileToString(filepath2));
        } else if (filepath1.endsWith(".yaml") && filepath2.endsWith((".yaml"))) {
            config1 = deserializeYamlToMap(fileToString(filepath1));
            config2 = deserializeYamlToMap(fileToString(filepath2));
        }

        Set<String> keys = new LinkedHashSet(config1.keySet());
        keys.addAll(config2.keySet());
        SortedSet<String> sortedKeys = new TreeSet(keys);

        for (String key: sortedKeys) {
            Object value1 = config1.get(key);
            Object value2 = config2.get(key);

            if (value1 == null) {
                value1 = "null";
            }
            if (value2 == null) {
                value2 = "null";
            }

            if (config1.containsKey(key) && config2.containsKey(key)) {
                if (value1.equals(value2)) {
                    nodesList.add(new Node(key, config1.get(key), "unchanged"));
                } else if (!value1.equals(value2)) {
                    nodesList.add(new Node(key, config2.get(key), "changed", config1.get(key)));
                }
            } else if (!config1.containsKey(key) && config2.containsKey(key)) {
                nodesList.add(new Node(key, config2.get(key), "added"));
            } else if (config1.containsKey(key) && !config2.containsKey(key)) {
                nodesList.add(new Node(key, config1.get(key), "deleted"));
            }
        }

        return Formatter.format(nodesList, "stylish");
    }

    public static String fileToString(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (Files.notExists(path)) {
            throw new Exception("File " + path + " is not exist");
        }

        return Files.readString(path);
    }

    public static Map deserializeJsonToMap(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Map.class);
    }

    public static Map deserializeYamlToMap(String yaml) throws JsonProcessingException {
        YAMLMapper yamlMapper = new YAMLMapper();
        return yamlMapper.readValue(yaml, Map.class);
    }
}
