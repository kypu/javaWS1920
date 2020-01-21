package de.frauas.java.projectWS1920.Parser.Implementation;

import de.frauas.java.projectWS1920.Bc.Validate;
import de.frauas.java.projectWS1920.Parser.ICommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.FileNotFoundException;

public abstract class AbstractCommandLineParser implements ICommandLineParser
{
    public abstract CommandLine parseArguments(String[] args) throws ParseException;

    public abstract Options getOptions();

    @Override
    public void printHelp()
    {
        Options options = getOptions();

        var formatter = new HelpFormatter();
        formatter.printHelp(this.getClass().toString(), options, true);
    }

    @Override
    public String getOpenFilePath(String[] args) throws FileNotFoundException
    {
        String filePath = args[0];
        Validate.isFile(filePath);

        return filePath;
    }
}
