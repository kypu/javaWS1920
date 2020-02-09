package de.frauas.java.projectWS1920.Logger.Implementation;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Custom logger class, that manages logs to console.
 */
public class MyLogger {
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ConsoleHandler consoleHandler;

    public MyLogger() {
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false); //turns off super console logs

        consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new MyConsoleFormatter());
        consoleHandler.setLevel(Level.WARNING);
        logger.addHandler(consoleHandler);
    }

    /**
     * logs to console
     * @param infoMessage
     */
    public void logToConsole(String infoMessage) {
        logger.warning(infoMessage);
    }
}
