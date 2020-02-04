package de.frauas.java.projectWS1920.Logger.Implementation;

import de.frauas.java.projectWS1920.Resources.Resource;
import org.apache.commons.logging.Log;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MyLogger {
    private String logFileName = Resource.getFilepath() + "Log.txt";
    private Logger logger;
    private ConsoleHandler consoleHandler;
    private FileHandler fileHandler;

    MyLogger() {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        try {
            fileHandler = new FileHandler(logFileName, true);
        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }

        fileHandler.setFormatter(new MyFileFormatter());
        consoleHandler.setFormatter(new MyConsoleFormatter());

        logger.addHandler(fileHandler);
        logger.addHandler(consoleHandler);
    }


}
