package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

@Command(name="differ", mixinStandardHelpOptions = true, version = "differ 1.0",
        description = "Return difference between .json-, .yaml- files.")
public class App {

    @Parameters(index = "0", paramLabel = "pathToFile1")
    String pathToFile1;
    @Parameters(index = "1", paramLabel = "pathToFile2")
    String pathToFile2;

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}