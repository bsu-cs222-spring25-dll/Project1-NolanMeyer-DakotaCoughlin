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
        ArrayList<Revision> revisionsList = new ArrayList<Revision>();

        JSONArray revisionArray = JsonPath.read(input,"$..revisions");
        JSONArray parsedRevisions = (JSONArray) revisionArray.getFirst();

        for(Object revision:parsedRevisions){
            LinkedHashMap<String,String> revisionConverted = (LinkedHashMap<String,String>) revision;
            revisionsList.add(new Revision(revisionConverted.get("user"),revisionConverted.get("timestamp")));
        }

        return revisionsList;
    }

}
