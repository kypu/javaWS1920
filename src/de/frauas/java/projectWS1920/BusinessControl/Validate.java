package de.frauas.java.projectWS1920.BusinessControl;

import de.frauas.java.projectWS1920.Exceptions.ValidationException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Validate {
    /**
     * Validates given file path.
     * @param filePath File path to check.
     * @return True if valid file path (for Tests).
     * @throws ValidationException
     */
    public static Boolean isFile(String filePath) throws ValidationException {
        try {
            File file = new File(filePath);
            if (!file.isFile()) throw new ValidationException();

        } catch (ValidationException ex) {
            throw ex;
        }
        return true;
    }
}
