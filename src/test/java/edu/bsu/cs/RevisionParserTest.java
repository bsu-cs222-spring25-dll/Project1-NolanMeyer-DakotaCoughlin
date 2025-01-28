package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionParserTest {

    @Test
    public void returnsFirstRevisionNameTest() throws IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        RevisionParser parser = new RevisionParser();
        Revision firstRevisionName = parser.parse(sampleFile);
        assertEquals("Miklogfeather",firstRevisionName.name);
    }

    @Test
    public void returnsFirstRevisionTimeStampTest() throws IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        RevisionParser parser = new RevisionParser();
        Revision firstRevisionTimeStamp = parser.parse(sampleFile);
        assertEquals("2023-09-07T18:34:43Z",firstRevisionTimeStamp.timeStampOfRevision);
    }

}
