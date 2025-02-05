package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;


public class RevisionInputStream {
    protected byte[] inputStream;
    public RevisionInputStream(InputStream inputStream){
        try{
            this.inputStream = inputStream.readAllBytes();
        }catch (IOException e) {
            ExceptionHandler.handleIOException(e, "Error while processing user input.");
        }

    }

}
