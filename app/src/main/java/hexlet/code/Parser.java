package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parseFile(String filepath) throws Exception {
        String file = fileToString(filepath);

        if (filepath.endsWith(".json")) {
            return deserializeJsonToMap(file);
        } else if (filepath.endsWith(".yaml") || filepath.endsWith(".yml")) {
            return deserializeYamlToMap(file);
        } else {
            throw new RuntimeException("Impossible parse specified file extension" + filepath);
        }
    }

    public static String fileToString(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (Files.notExists(path)) {
            throw new Exception("File " + path + " is not exist");
        }

        return Files.readString(path);
    }

    public static Map<String, Object> deserializeJsonToMap(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Map.class);
    }

    public static Map<String, Object> deserializeYamlToMap(String yaml) throws JsonProcessingException {
        YAMLMapper yamlMapper = new YAMLMapper();
        return yamlMapper.readValue(yaml, Map.class);
    }
}
