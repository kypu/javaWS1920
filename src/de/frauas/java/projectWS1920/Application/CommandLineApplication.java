package de.frauas.java.projectWS1920.Application;

import de.frauas.java.projectWS1920.BusinessControl.ConnectUI;
import de.frauas.java.projectWS1920.Parser.Implementation.OperationParser;
import org.apache.commons.cli.*;
import de.frauas.java.projectWS1920.BusinessControl.Validate;
import de.frauas.java.projectWS1920.Logger.Implementation.MyLogger;
import de.frauas.java.projectWS1920.Parser.ICommandLineParser;


import java.util.Arrays;

public class CommandLineApplication {
    private MyLogger logger;
    private ConnectUI connectUI;
    private ICommandLineParser operationParser;

    CommandLineApplication(MyLogger logger) {
        this.logger = logger;
        connectUI = new ConnectUI(logger);
        operationParser = new OperationParser();
    }

    /**
     * Entry point to program.
     * @param args Given command line arguments.
     */
    public void run(String[] args) {

        try {
            String filePath = operationParser.getOpenFilePath(args);
            Validate.isFile(filePath);

            String[] argsSlice = Arrays.copyOfRange(args, 1, args.length);

            // If only file path was given as argument.
            if (argsSlice.length == 0) {
                connectUI.logBasicGraphAttributes(filePath);
                System.exit(0);
            }

            CommandLine line = operationParser.parseArguments(argsSlice);


            if (line.hasOption("b")) {
                var optionIntValue = Integer.parseInt(line.getOptionValue("b"));
                connectUI.logBetweennessCentrality(filePath, optionIntValue);
                System.exit(0);
            }
            if (line.hasOption("a")) {
                var optionOutputFilePath = line.getOptionValue("a");
                connectUI.logCreateOutputFile(filePath, optionOutputFilePath);
                System.exit(0);
            }
            if (line.hasOption("s")) {
                var idSourceNode = Integer.parseInt(line.getOptions()[0].getValue(0));
                var idDestinationNode = Integer.parseInt(line.getOptions()[0].getValue(1));
                connectUI.logShortestPath(filePath, idSourceNode, idDestinationNode);
                System.exit(0);
            }
        } catch (InterruptedException ex) {
            logger.logToConsoleAndFile("Timeout during calculation of shortest paths.");
        } catch (Exception ex) {
            logger.logToConsoleAndFile("Program terminated due to invalid arguments.\r\n");
            logger.logToFile(ex.getStackTrace().toString());
            operationParser.printHelp();
        } finally {
            System.exit(0);
        }
    }
}