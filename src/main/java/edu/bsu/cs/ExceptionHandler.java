package edu.bsu.cs;

public class ExceptionHandler {

    public static void handleException(Exception e, String contextMessage) {
        System.err.printf("Error Occurred: %s, %s\n",e.toString(),contextMessage);
    }
}
