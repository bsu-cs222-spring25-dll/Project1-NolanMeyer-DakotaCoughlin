package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionParserTest {

    @Test
    public void returnsFirstRevisionNameTest() throws IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        RevisionParser parser = new RevisionParser();
        List<Revision> firstRevisionName = parser.parse(sampleFile);
        assertEquals("Miklogfeather",firstRevisionName.getFirst().name);
    }

    @Test
    public void returnsFirstRevisionTimeStampTest() throws IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        RevisionParser parser = new RevisionParser();
        List<Revision> firstRevisionTimeStamp = parser.parse(sampleFile);
        assertEquals("2023-09-07T18:34:43Z",firstRevisionTimeStamp.getFirst().timeStampOfRevision);
    }

    @Test
    public void returnsListOfSize4() throws IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        RevisionParser parser = new RevisionParser();
        List<Revision> revisionList = parser.parse(sampleFile);
        assertEquals(4,revisionList.size());
    }

    @Test
    public void extraRevisionsTest() throws IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        RevisionParser parser = new RevisionParser();
        JSONArray revisions = parser.extractRevisions(sampleFile);
        assertEquals(4,revisions.size());

    }

    @Test
    public void convertRevisionsToListTest() throws IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        RevisionParser parser = new RevisionParser();
        JSONArray parsedRevisions = parser.extractRevisions(sampleFile);
        List<Revision> revisions = parser.convertRevisionsToList(parsedRevisions);
        assertEquals(4,revisions.size());
    }

}
