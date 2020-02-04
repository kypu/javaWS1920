package de.frauas.java.projectWS1920.Logger.Implementation;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public abstract class AbstractMyFormatter extends Formatter {

    public abstract String format(LogRecord record);
}
