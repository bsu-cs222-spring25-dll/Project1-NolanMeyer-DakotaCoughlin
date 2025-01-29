package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RevisionParser {

    public List<Revision> parse(InputStream input) throws IOException {
        JSONArray parsedRevisions = extractRevisions(input);
        return convertRevisionsToList(parsedRevisions);
    }

    protected JSONArray extractRevisions(InputStream inputStream) throws IOException {
        JSONArray revisionArray = JsonPath.read(inputStream,"$..revisions");
        return (JSONArray) revisionArray.getFirst();
    }

    protected List<Revision> convertRevisionsToList(JSONArray array) {
        ArrayList<Revision> revisionsList = new ArrayList<>();

        for(Object revision:array){
            LinkedHashMap<String,String> revisionConverted = (LinkedHashMap<String,String>) revision;
            revisionsList.add(new Revision(revisionConverted.get("user"),revisionConverted.get("timestamp")));
        }
    return revisionsList;
    }
}
