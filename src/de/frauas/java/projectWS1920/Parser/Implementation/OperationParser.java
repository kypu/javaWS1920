package de.frauas.java.projectWS1920.Parser.Implementation;

import org.apache.commons.cli.*;

public class OperationParser extends AbstractCommandLineParser {

    /**
     * Attempts to parse arguments and create a CommandLine,
     * throws exception if it fails.
     * @param args List of string arguments.
     * @return CommandLine.
     * @throws ParseException If arguments can't be parsed.
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
     * Sets up command line argument options. All of them share an OptionsGroup,
     * which makes sure only one is used at a time.
     * @return Command line argument Options.
     */
    @Override
    public Options getOptions() {
        var options = new Options();

        Option.Builder betweennessOptionBuilder  = Option.builder("b")
                .longOpt("betweenness")
                .desc("Betweenness centrality measure for given node.")
                .hasArg(true)
                .numberOfArgs(1);
        Option betweennessOption = betweennessOptionBuilder.build();

        Option.Builder outputOptionBuilder  = Option.builder("a")
                .longOpt("createoutput")
                .desc("Calculate all above graph and save it into file.")
                .hasArg(true)
                .numberOfArgs(1);
        Option outputOption = outputOptionBuilder.build();

        Option.Builder shortestPathOptionBuilder  = Option.builder("s")
                .longOpt("shortestpath")
                .desc("Shortest path between the two given vertices according to Dijkstra algorithm.")
                .hasArgs()
                .numberOfArgs(2);
        Option shortestPathOption = shortestPathOptionBuilder.build();

        OptionGroup operationGroup = new OptionGroup();
        operationGroup.addOption(outputOption);
        operationGroup.addOption(betweennessOption);
        operationGroup.addOption(shortestPathOption);
        operationGroup.isRequired();

        options.addOptionGroup(operationGroup);
        return options;
    }

    @Override
    public void printHelp()
    {
        Options options = getOptions();

        var formatter = new HelpFormatter();
        formatter.printHelp("Enter a valid file path and add up to one of the following options:",
                this.getOptions(), true);
    }
}