package de.frauas.java.projectWS1920.Logger.Implementation;

import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public abstract class AbstractMyFormatter extends Formatter {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String newLine = "\r\n";
    public static final String separator = "- - - - - - - - - - - - - - - - - -\r\n";

    public abstract String format(LogRecord record);
}
