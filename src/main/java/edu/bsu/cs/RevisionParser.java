package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class RevisionParser {

    public Revision parse(InputStream input) throws IOException {
        JSONArray result = (JSONArray) JsonPath.read(input,"$..revisions");
        JSONArray revisions = (JSONArray) result.getFirst();
        LinkedHashMap<String,String> firstRevision = (LinkedHashMap<String, String>) revisions.getFirst();
        return new Revision(firstRevision.get("user"),firstRevision.get("timestamp"));
    }

}
