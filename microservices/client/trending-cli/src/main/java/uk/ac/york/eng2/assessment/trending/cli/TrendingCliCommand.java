package uk.ac.york.eng2.assessment.trending.cli;

import io.micronaut.configuration.picocli.PicocliRunner;
import main.java.uk.ac.york.eng2.assessment.trending.cli.hashtag.ListHashtagCommand;
import main.java.uk.ac.york.eng2.assessment.trending.cli.health.PingHealthCommand;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "trending-cli", description = "...",
        mixinStandardHelpOptions = true, subcommands = {
            ListHashtagCommand.class,
            PingHealthCommand.class
        })
public class TrendingCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(TrendingCliCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
