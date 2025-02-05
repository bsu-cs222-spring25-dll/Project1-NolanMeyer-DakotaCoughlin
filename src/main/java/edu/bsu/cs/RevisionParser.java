package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RevisionParser {

    protected RevisionInputStream inputStream;

    public RevisionParser(RevisionInputStream inputStream){
        this.inputStream = inputStream;
    }

    public List<Revision> parse() throws IOException {
        JSONArray parsedRevisions = extractRevisions(new ByteArrayInputStream(this.inputStream.inputStream));
        return convertRevisionsToList(parsedRevisions);
    }

    protected JSONArray extractRevisions(InputStream inputStream) throws IOException {
        JSONArray revisionArray = JsonPath.read(inputStream,"$..revisions");
        return (JSONArray) revisionArray.getFirst();
    }

    protected List<Revision> convertRevisionsToList(JSONArray array) {
        ArrayList<Revision> revisionsList = new ArrayList<>();

        for(Object revision:array){
            if(revision instanceof LinkedHashMap<?,?>) {
                @SuppressWarnings("unchecked")
                LinkedHashMap<String, String> revisionConverted = (LinkedHashMap<String, String>) revision;
                revisionsList.add(new Revision(revisionConverted.get("user"), revisionConverted.get("timestamp")));
            }
        }
        return revisionsList;
    }

    public String extractRedirect(InputStream inputStream) throws IOException {
        String output = "";
        JSONArray parsedRedirect = JsonPath.read(inputStream,"$..to");

        if(!parsedRedirect.isEmpty()){
            output = String.format("Redirected to %s",parsedRedirect.getFirst().toString());
        }

        return output;
    }
}
