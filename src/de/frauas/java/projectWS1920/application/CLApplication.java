package de.frauas.java.projectWS1920.application;

import de.frauas.java.projectWS1920.Bc.Validate;
import de.frauas.java.projectWS1920.Parser.ICommandLineParser;
import org.apache.commons.cli.*;
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.math3.stat.StatUtils;

import java.util.Arrays;

public class CLApplication<P>
{
    /*
    possible exceptions:
    - no args at all +
    - no file path +
    - invalid file path (open) +
    - invalid file path (save) -
    - no parameter for option +
    - more than one thing done ?
     */
    public void run(String[] args)
    {
        try
        {
            ICommandLineParser argumentParser;

            String filePath = args[0];
            Validate.isFile(filePath);

            String[] argsSlice = Arrays.copyOfRange(args,1, args.length);
            CommandLine line = parseArguments(argsSlice);

            // Do da magic
            System.out.println("file path: " + filePath);
            System.out.println("option value: " + line.getOptionValue("a"));
            //if line.hasOption(..)
        } catch (Exception ex)
        {
            /*
            System.err.println("Failed to parse command line arguments");
            System.err.println(ex.toString());
            ex.printStackTrace();
            printAppHelp();
            */
            System.out.println("Caught something, brother!");
            System.exit(1);
        }
    }

    private CommandLine parseArguments(String[] args) throws ParseException {
        Options options = getOptions();
        CommandLine line = null;

        CommandLineParser parser = new DefaultParser();

        try
        {
            line = parser.parse(options, args);
        } catch (ParseException ex)
        {
            throw ex;
        }
        return line;
    }

    private Options getOptions()
    {
        var options = new Options();

        Option shortestPathOption = new Option("s", "shortestpath", true,
                "Shortest path between the two given vertices according to Dijkstra algorithm.");
        Option betweennessOption = new Option("b", "bcm", true,
                "Betweenness centrality measure for given node.");
        Option allCalcOption = new Option("a", "allcalc", true,
                "Calculate all above graph and save it into file.");

        OptionGroup operationGroup = new OptionGroup();
        operationGroup.addOption(shortestPathOption);
        operationGroup.addOption(betweennessOption);
        operationGroup.addOption(allCalcOption);
        operationGroup.isRequired();

        options.addOptionGroup(operationGroup);
        return options;
    }

    private void printHelp()
    {
        Options options = getOptions();

        var formatter = new HelpFormatter();
        formatter.printHelp("JavaStatsEx", options, true);
    }
}
