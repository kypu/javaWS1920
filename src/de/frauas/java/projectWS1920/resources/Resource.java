package de.frauas.java.projectWS1920.resources;

import org.apache.commons.io.FilenameUtils;

public class Resource
{
    private static final String filepath = "src\\de\\frauas\\java\\projectWS1920\\resources\\";

    /**
     * Converts file path to resource folder based on OS that's used (differentiation between Windows and Unix)
     * @return Converted file path.
     */
    public static String getFilepath()
    {
        return FilenameUtils.separatorsToSystem(filepath);
    }
}
