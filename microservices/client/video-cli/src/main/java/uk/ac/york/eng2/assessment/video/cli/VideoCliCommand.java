package uk.ac.york.eng2.assessment.video.cli;

import io.micronaut.configuration.picocli.PicocliRunner;
import main.java.uk.ac.york.eng2.assessment.video.cli.user.CreateUserCommand;
import main.java.uk.ac.york.eng2.assessment.video.cli.video.CreateVideoCommand;
import main.java.uk.ac.york.eng2.assessment.video.cli.video.DislikeVideoCommand;
import main.java.uk.ac.york.eng2.assessment.video.cli.video.LikeVideoCommand;
import main.java.uk.ac.york.eng2.assessment.video.cli.video.ListVideoCommand;
import main.java.uk.ac.york.eng2.assessment.video.cli.video.WatchVideoCommand;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "video-cli", description = "...",
        mixinStandardHelpOptions = true, subcommands = {
            CreateVideoCommand.class,
            DislikeVideoCommand.class,
            LikeVideoCommand.class,
            ListVideoCommand.class,
            WatchVideoCommand.class,
            CreateUserCommand.class
        })
public class VideoCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(VideoCliCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
