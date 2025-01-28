package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class RevisionParser {

    public String parse(InputStream input) throws IOException {
        JSONArray result = JsonPath.read(input,"$..user");
        return result.getFirst().toString();
    }

}
