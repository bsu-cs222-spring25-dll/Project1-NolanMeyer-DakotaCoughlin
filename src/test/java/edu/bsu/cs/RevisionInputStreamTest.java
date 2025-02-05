package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionInputStreamTest {

    @Test
    public void returnsInputStreamTest(){
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        RevisionInputStream revisionInputStream = new RevisionInputStream(sampleFile);
        assertInstanceOf(ByteArrayInputStream.class, revisionInputStream.openInputStream());
    }
}
