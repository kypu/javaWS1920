package de.frauas.java.projectWS1920.Logger.Implementation;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class MyFileFormatter extends AbstractMyFormatter {

    @Override
    public String format(LogRecord record) {
        String recordString = "";
        int recordIntValue = record.getLevel().intValue();
        /*
        return record.getThreadID()+"::"+record.getSourceClassName()+"::"
                +record.getSourceMethodName()+"::"
                +new Date(record.getMillis())+"::"
                +record.getMessage()+"\n";
         */
        switch(recordIntValue) {
            case 0: //Level.SEVERE.intValue(): //SEVERE
            case 1: //WARNING
                //TODO
                break;
            case 2: //INFO
            case 3: //CONFIG
                //TODO
                break;
            default: //FINE, FINER, FINEST
                //TODO
        }
        return recordString;
    }
}
