package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;

public class RevisionInputStream {
    protected byte[] inputStream;
    public RevisionInputStream(InputStream inputStream) throws IOException {
        this.inputStream = inputStream.readAllBytes();
    }

}
