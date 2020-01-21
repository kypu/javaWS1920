package de.frauas.java.projectWS1920.Parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.FileNotFoundException;

public interface ICommandLineParser
{
    CommandLine parseArguments(String[] args) throws ParseException;
    Options getOptions();
    void printHelp();
    String getOpenFilePath(String[] args) throws FileNotFoundException;
}