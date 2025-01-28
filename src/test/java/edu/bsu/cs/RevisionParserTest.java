package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionParserTest {

    @Test
    public void returnsFirstRevisionTest() throws IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        RevisionParser parser = new RevisionParser();
        String firstRevisionName = parser.parse(sampleFile);
        assertEquals("Miklogfeather",firstRevisionName);
    }

}
