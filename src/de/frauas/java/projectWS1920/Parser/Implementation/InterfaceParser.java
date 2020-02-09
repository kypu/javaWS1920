package de.frauas.java.projectWS1920.Parser.Implementation;

import org.apache.commons.cli.*;

/**
 * This class was discarded during development, since we wanted the user to run
 * actions immediately from the command line, rather then select the user interface
 * first.
 * We kept it nevertheless, since it demonstrates how our parser interface could
 * be used to easily extend the program.
 */
public class InterfaceParser extends AbstractCommandLineParser {
    /**
     * Sets up command line options. All of them share an OptionsGroup,
     * which makes sure only one is used at a time.
     * @return Command line argument Options.
     */
    @Override
    public CommandLine parseArguments(String[] args) throws ParseException {
        Options options = getOptions();
        CommandLine line = null;

        CommandLineParser parser = new DefaultParser();

        try {
            line = parser.parse(options, args);
        } catch (ParseException ex) {
            throw ex;
        }
        return line;
    }

    /**
     * Sets up command line options. All of them share an OptionsGroup,
     * which makes sure only one is used at a time.
     * @return Command line argument Options.
     */
    @Override
    public Options getOptions() {
        var options = new Options();

        Option guiOption = new Option("g", "gui", true,
                "Starts program in graphical user interface.");
        Option cliOption = new Option("c", "cli", true,
                "Starts program in command line interface.");

        OptionGroup operationGroup = new OptionGroup();
        operationGroup.addOption(guiOption);
        operationGroup.addOption(cliOption);
        operationGroup.isRequired();

        options.addOptionGroup(operationGroup);
        return options;
    }

    @Override
    public void printHelp() {
        Options options = getOptions();

        var formatter = new HelpFormatter();
        formatter.printHelp("", options, true);
    }
}
