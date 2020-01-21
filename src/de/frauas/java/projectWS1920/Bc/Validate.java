package de.frauas.java.projectWS1920.Bc;

import java.io.File;
import java.io.FileNotFoundException;
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
    public static Boolean filepath(String path) throws FileNotFoundException {
        try
        {
            File file = new File(path);
            if (!file.isFile()) throw new FileNotFoundException();

        } catch (FileNotFoundException ex)
        {
            throw ex;
        }
        return true;
    }
}
