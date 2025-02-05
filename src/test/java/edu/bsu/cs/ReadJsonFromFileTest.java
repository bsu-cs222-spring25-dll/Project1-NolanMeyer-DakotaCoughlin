package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReadJsonFromFileTest {
    @Test
    public void testAccessToJsonFile(){
            String jsonData = readSampleFileAsString();
            Assertions.assertNotNull(jsonData);
    }

    @Test
    public void testCountRevisionsWithJsonPath(){
            String jsonData = readSampleFileAsString();
            JSONArray revisions = getRevisionsFromJson(jsonData);
            Assertions.assertEquals(4, revisions.size());
    }

    private String readSampleFileAsString(){
        try{
            try (InputStream sampleFile = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("sample.json")) {
                return new String(Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
            }
        }catch (IOException e) {
            ExceptionHandler.handleIOException(e, "Error while processing user input.");
        }catch (NullPointerException e) {
            ExceptionHandler.handleNullPointerException(e, "Null Pointer Error.");
        }
        String failed = "";
        return failed;
    }

    private JSONArray getRevisionsFromJson(String jsonData) {
        return JsonPath.read(jsonData, "$..revisions[*]");
    }

}

