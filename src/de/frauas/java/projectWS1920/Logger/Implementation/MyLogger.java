package de.frauas.java.projectWS1920.Logger.Implementation;

import de.frauas.java.projectWS1920.Resources.Resource;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Custom logger class, that manages logs to console and file.
 * A single log file is used, new logs get appended.
 */
public class MyLogger {
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String logFileName = Resource.getFilepath() + "Log.txt";
    private ConsoleHandler consoleHandler;
    private FileHandler fileHandler;

    public MyLogger() {
        try {
            fileHandler = new FileHandler(logFileName, true);
        } catch (IOException e) {
            // In case the file handler can't be created, the stacktrace is printed to console
            e.printStackTrace();
        }
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false); //turns off super console logs

        fileHandler.setFormatter(new MyFileFormatter());
        fileHandler.setLevel(Level.ALL);
        logger.addHandler(fileHandler);

        consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new MyConsoleFormatter());
        consoleHandler.setLevel(Level.WARNING);
        logger.addHandler(consoleHandler);
    }

    /**
     * Logs given message to console and file.
     * @param infoMessage Message to log.
     */
    public void logToConsoleAndFile(String infoMessage){
        logger.warning(infoMessage);
    }

    /**
     * Appends given message to file log.
     * @param infoMessage Message to log.
     */
    public void logToFile(String infoMessage){
        logger.info(infoMessage);
    }
}
