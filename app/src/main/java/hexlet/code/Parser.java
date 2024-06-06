package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String data, String type) throws Exception {
        return switch (type) {
            case "json" -> deserializeJsonToMap(data);
            case "yaml", "yml" -> deserializeYamlToMap(data);
            default -> throw new RuntimeException("Impossible to parse data with type: " + type);
        };
    }

    private static Map<String, Object> deserializeJsonToMap(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Map.class);
    }

    private static Map<String, Object> deserializeYamlToMap(String yaml) throws JsonProcessingException {
        YAMLMapper yamlMapper = new YAMLMapper();
        return yamlMapper.readValue(yaml, Map.class);
    }
}
