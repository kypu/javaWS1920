package de.frauas.java.projectWS1920.Logger.Implementation;

import java.util.logging.LogRecord;

public class MyConsoleFormatter extends AbstractMyFormatter {
    /**
     * Formats given LogRecord to our console output.
     * Only the message is displayed on the console.
     * Changes color to yellow.
     * @param record Given LogRecord.
     * @return Formatted LogRecord as String.
     */
    @Override
    public String format(LogRecord record) {
        StringBuilder recordBuilder = new StringBuilder();

        recordBuilder.append(record.getMessage());

        return recordBuilder.toString();
    }
}
