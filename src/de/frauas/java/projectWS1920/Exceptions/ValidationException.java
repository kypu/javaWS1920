package de.frauas.java.projectWS1920.Exceptions;

public class ValidationException extends Exception {
    public ValidationException()
    {
        super();
    }

    public ValidationException(String errorMessage)
    {
        super(errorMessage);
    }
}
