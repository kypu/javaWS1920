package de.frauas.java.projectWS1920.Bc;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/*
Validates given inputs.
 */
public class Validate
{
    /*
    Validates given file path.
     */
    public static Boolean filepath(String path)
    {
        try
        {
            Paths.get(path);

        } catch (InvalidPathException ex)
        {
            throw ex;
        }
        return true;
    }
}
