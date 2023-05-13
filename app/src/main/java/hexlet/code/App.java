package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "Path to first file")
    String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "Path to second file")
    String filepath2;
    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "Choose format to compare",
    defaultValue = "stylish")
    String option;

    @Override
    public Integer call() throws Exception {
        Differ.generate(filepath1, filepath2, option);
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
