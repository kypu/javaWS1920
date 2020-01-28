package de.frauas.java.projectWS1920.BusinessControl;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MyLogger {
    private Logger logger;
    private FileHandler fileHandler;

    MyLogger()
    {
        logger.setUseParentHandlers(false); // turns off console logs
    }


}
