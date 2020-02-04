package de.frauas.java.projectWS1920.Parser.Implementation;

import org.apache.commons.cli.*;

public class OperationParser extends AbstractCommandLineParser {
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

    @Override
    public Options getOptions() {
        var options = new Options();

        Option shortestPathOption = new Option("s", "shortestpath", true,
                "Shortest path between the two given vertices according to Dijkstra algorithm.");
        Option betweennessOption = new Option("b", "bcm", true,
                "Betweenness centrality measure for given node.");
        Option allCalcOption = new Option("a", "allcalc", true,
                "Calculate all above graph and save it into file.");
        // + nur Dateiname auch Option

        OptionGroup operationGroup = new OptionGroup();
        operationGroup.addOption(shortestPathOption);
        operationGroup.addOption(betweennessOption);
        operationGroup.addOption(allCalcOption);
        //operationGroup.isRequired();

        options.addOptionGroup(operationGroup);
        return options;
    }
}