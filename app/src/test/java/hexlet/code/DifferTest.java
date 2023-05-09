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
}
