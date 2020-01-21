package de.frauas.java.projectWS1920.Bc;

import de.frauas.java.projectWS1920.Exceptions.ValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Needs refactoring.. ugly af
 */
public class Validate
{
    public static Boolean isFile(String filePath) throws ValidationException {
        try
        {
            File file = new File(filePath);
            if (!file.isFile()) throw new ValidationException();

        } catch (ValidationException ex)
        {
            throw ex;
        }
        return true;
    }

    public static Boolean isPath(String filePath) throws ValidationException
    {
        try
        {
            Path path = Paths.get(filePath);
            if (Files.notExists(path)) throw new ValidationException();
        } catch (ValidationException ex)
        {
            throw ex;
        }
        return true;
    }
} // end of class Validate
