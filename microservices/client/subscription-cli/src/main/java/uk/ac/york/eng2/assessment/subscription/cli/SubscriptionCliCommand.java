package uk.ac.york.eng2.assessment.subscription.cli;

import io.micronaut.configuration.picocli.PicocliRunner;
import main.java.uk.ac.york.eng2.assessment.subscription.cli.hashtags.ListHashtagsCommand;
import main.java.uk.ac.york.eng2.assessment.subscription.cli.hashtags.SubscribeHashtagsCommand;
import main.java.uk.ac.york.eng2.assessment.subscription.cli.hashtags.UnsubscribeHashtagsCommand;
import main.java.uk.ac.york.eng2.assessment.subscription.cli.health.PingHealthCommand;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "subscription-cli", description = "...",
        mixinStandardHelpOptions = true, subcommands = {ListHashtagsCommand.class, SubscribeHashtagsCommand.class, UnsubscribeHashtagsCommand.class, PingHealthCommand.class})
public class SubscriptionCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(SubscriptionCliCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
