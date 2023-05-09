package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void compareFlatJson() throws Exception {
        Path path = Paths.get("src/test/resources/exampleJson.txt");
        String example = Files.readString(path);

        assertEquals(example, Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
    }

    @Test
    public void compareFlatYaml() throws Exception {
        Path path = Paths.get("src/test/resources/exampleJson.txt");
        String example = Files.readString(path);

        assertEquals(example, Differ.generate("src/test/resources/yaml1.yaml", "src/test/resources/yaml2.yaml"));
    }

    @Test
    public void compareNestedJson() throws Exception {
        Path path = Paths.get("src/test/resources/nestedJsonExample.txt");
        String example = Files.readString(path);

        assertEquals(example, Differ.generate(
                "src/test/resources/nestedJson1.json",
                "src/test/resources/nestedJson2.json"));
    }

    @Test
    public void compareNestedJsonToPlain() throws Exception {
        Path path = Paths.get("src/test/resources/examplePlain.txt");
        String example = Files.readString(path);

        assertEquals(example, Differ.generate(
                "src/test/resources/nestedJson1.json",
                "src/test/resources/nestedJson2.json",
                "plain"
                ));
    }
}
