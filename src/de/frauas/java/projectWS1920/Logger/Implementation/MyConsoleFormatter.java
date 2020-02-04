package de.frauas.java.projectWS1920.Logger.Implementation;

import java.util.Date;
import java.util.logging.LogRecord;

public class MyConsoleFormatter extends AbstractMyFormatter {

    @Override
    public String format(LogRecord record) {
        return record.getThreadID() + "::" + record.getSourceClassName() + "::"
                + record.getSourceMethodName() + "::"
                + new Date(record.getMillis()) + "::"
                + record.getMessage() + "\n";
    }
}
