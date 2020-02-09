package de.frauas.java.projectWS1920.Parser.Implementation;

import de.frauas.java.projectWS1920.BusinessControl.Validate;
import de.frauas.java.projectWS1920.Exceptions.ValidationException;
import de.frauas.java.projectWS1920.Parser.ICommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public abstract class AbstractCommandLineParser implements ICommandLineParser
{
    public abstract CommandLine parseArguments(String[] args) throws ParseException;

    public abstract Options getOptions();

    public abstract void printHelp();

    @Override
    public String getOpenFilePath(String[] args) throws ValidationException
    {
        String filePath = args[0];
        Validate.isFile(filePath);

        return filePath;
    }
}
