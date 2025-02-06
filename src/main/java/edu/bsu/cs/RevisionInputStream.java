package edu.bsu.cs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class RevisionInputStream {
    protected byte[] inputStream;
    public RevisionInputStream(InputStream inputStream){
        try{
            this.inputStream = inputStream.readAllBytes();
        }catch (Exception e) {
            ExceptionHandler.handleException(e,"Couldn't find Wikipedia Article");
        }
    }

    public ByteArrayInputStream openInputStream() {
        return new ByteArrayInputStream(inputStream);
    }
}
