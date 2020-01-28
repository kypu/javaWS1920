package de.frauas.java.projectWS1920.Bc;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MyLogger
{
    private Logger logger;
    private FileHandler fileHandler;

    MyLogger()
    {
        logger.setUseParentHandlers(false); // turns off console logs
    }

    //log to file

    //log to console
}
