package de.frauas.java.projectWS1920.Logger.Implementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.LogRecord;

public class MyFileFormatter extends AbstractMyFormatter {

    /**
     * Formats given LogRecord to our file output.
     * Status, datetime and message are being logged.
     * @param record Given LogRecord.
     * @return Formatted LogRecord as String.
     */
    @Override
    public String format(LogRecord record) {
        StringBuilder recordBuilder = new StringBuilder();
        int recordIntValue = record.getLevel().intValue();

        switch(recordIntValue) {
            case 1000: //SEVERE
            case 900: //WARNING
                recordBuilder.append("CONSOLE LOG" + newLine);
                break;
            case 800: //INFO
            case 700: //CONFIG
                recordBuilder.append("FILE LOG:" + newLine);
                break;
            default: //FINE (600), FINER (500), FINEST (400)
                recordBuilder.append("" + newLine);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        Date date = new Date(record.getMillis());
        recordBuilder.append(dateFormat.format(date) + newLine);

        recordBuilder.append(this.formatMessage(record) + newLine);

        return recordBuilder.append(separator).toString();
    }
}