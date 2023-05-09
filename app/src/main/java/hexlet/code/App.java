package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App {
    @Parameters(index = "0", paramLabel = "filepath1", description = "Path to first file")
    String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "Path to second file")
    String filepath2;
    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "Choose format to compare")
    String option;

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
