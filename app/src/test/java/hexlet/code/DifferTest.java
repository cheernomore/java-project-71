package hexlet.code;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private Path getFixturePath(String filename) {
        return Paths.get("src/test/resources/fixtures", filename).toAbsolutePath().normalize();
    }

    private String readFixture(String filename) throws Exception {
        Path path = getFixturePath(filename);
        return Files.readString(path);
    }

    @Test
    public void compareFlatJson() throws Exception {
        String example = readFixture("exampleJson.txt");
        assertEquals(example, Differ.generate(getFixturePath("file1.json").toString(),
                getFixturePath("file2.json").toString()));
    }

    @Test
    public void compareFlatYaml() throws Exception {
        String example = readFixture("exampleYaml.txt");
        assertEquals(example, Differ.generate(getFixturePath("yaml1.yaml").toString(),
                getFixturePath("yaml2.yaml").toString()));
    }

    @Test
    public void compareNestedJson() throws Exception {
        String example = readFixture("nestedJsonExample.txt");
        assertEquals(example, Differ.generate(getFixturePath("nestedJson1.json").toString(),
                getFixturePath("nestedJson2.json").toString()));
    }

    @Test
    public void compareNestedJsonToPlain() throws Exception {
        String example = readFixture("examplePlain.txt");
        assertEquals(example, Differ.generate(getFixturePath("nestedJson1.json").toString(),
                getFixturePath("nestedJson2.json").toString(), "plain"));
    }

    @Test
    public void compareFormattedJson() throws Exception {
        String example = readFixture("formatterJsonExample.json");
        JSONAssert.assertEquals(example, Differ.generate(getFixturePath("formatterJson1.json").toString(),
                getFixturePath("formatterJson2.json").toString(), "json"), true);
    }
}
