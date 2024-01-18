package uk.ac.york.eng2.assessment.recommendation.cli;

import io.micronaut.configuration.picocli.PicocliRunner;
import main.java.uk.ac.york.eng2.assessment.recommendation.cli.hashtags.ListHashtagsCommand;
import main.java.uk.ac.york.eng2.assessment.recommendation.cli.health.PingHealthCommand;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "recommendation-cli", description = "...",
        mixinStandardHelpOptions = true, commands = {ListHashtagsCommand.class, PingHealthCommand.class})
public class RecommendationCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(RecommendationCliCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
