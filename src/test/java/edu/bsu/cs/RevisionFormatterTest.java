package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionFormatterTest {

    @Test
    public void formatOutputTest(){
        RevisionFormatter revisionFormatter = new RevisionFormatter();
        Revision revisionTest = new Revision("Miklogfeather","2023-09-07T18:34:43Z");
        String formattedOutput = revisionFormatter.formatOutput(revisionTest);
        assertEquals("2023-09-07T18:34:43Z  Miklogfeather\n",formattedOutput);

    }

}
