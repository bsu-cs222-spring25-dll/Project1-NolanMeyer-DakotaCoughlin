package edu.bsu.cs;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(ExceptionHandler.class.getName());

    /**
     * Handles IOExceptions with a custom error message
     */
    public static void handleIOException(IOException e, String contextMessage) {
        LOGGER.log(Level.SEVERE, contextMessage, e);
        System.err.println("I/O Error: " + contextMessage);
    }
    public static void handleNullPointerException(NullPointerException e, String contextMessage) {
        LOGGER.log(Level.SEVERE, contextMessage, e);
        System.err.println("I/O Error: " + contextMessage);
    }
}
