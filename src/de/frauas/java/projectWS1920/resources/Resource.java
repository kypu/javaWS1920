package de.frauas.java.projectWS1920.resources;

import org.apache.commons.io.FilenameUtils;
public class Resource
{
    private static final String filepath = "src\\de\\frauas\\java\\projectWS1920\\resources\\";

    public static String getFilepath() { return FilenameUtils.separatorsToSystem(filepath); }
}
