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
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "Path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "Choose format to compare",
            defaultValue = "stylish")
    private String option;

    @Override
    public Integer call() throws Exception {
        // Вызов Differ.generate() и возврат кода выхода
        String output = Differ.generate(filepath1, filepath2, option);
        System.out.println(output);
        return 0;
    }

    public static void main(String... args) {
        try {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
