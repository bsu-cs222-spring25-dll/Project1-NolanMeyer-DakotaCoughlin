package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionFormatterTest {

    @Test
    public void formatOutputTest(){
        RevisionFormatter revisionFormatter = new RevisionFormatter();
        Revision revisionTest = new Revision("Miklogfeather","2023-09-07T18:34:43Z");
        String formattedOutput = revisionFormatter.formatOutput(revisionTest);
        assertEquals("2023-09-07T18:34:43Z  Miklogfeather\n",formattedOutput);
    }

    @Test
    public void printRevisionListTest(){
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        RevisionParser parser = new RevisionParser(new RevisionInputStream(sampleFile));
        List<Revision> revisionList = parser.parse();
        RevisionFormatter formatter = new RevisionFormatter();
        String test = formatter.printRevisionList(revisionList);
        assertEquals("1  2023-09-07T18:34:43Z  Miklogfeather\n" +
                "2  2023-09-07T17:21:48Z  ModernDayTrilobite\n" +
                "3  2023-09-02T15:06:03Z  Freefry\n" +
                "4  2023-09-02T15:05:04Z  Freefry\n",formatter.printRevisionList(revisionList));
    }

}
