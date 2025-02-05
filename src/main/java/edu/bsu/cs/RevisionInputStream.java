package edu.bsu.cs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class RevisionInputStream {
    protected byte[] inputStream;
    public RevisionInputStream(InputStream inputStream){
        try{
            this.inputStream = inputStream.readAllBytes();
        }catch (Exception e) {
            System.err.println("Could not read from Wikipedia!");
        }
    }

    public ByteArrayInputStream openInputStream() {
        return new ByteArrayInputStream(inputStream);
    }
}
