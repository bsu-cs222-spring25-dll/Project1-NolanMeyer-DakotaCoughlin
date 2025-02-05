package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class RevisionParser {

    protected RevisionInputStream inputStreamInstance;

    public RevisionParser(RevisionInputStream inputStream){
        this.inputStreamInstance = inputStream;
    }

    public List<Revision> parse(){

            JSONArray parsedRevisions = extractRevisions(new ByteArrayInputStream(this.inputStreamInstance.inputStream));
            return convertRevisionsToList(parsedRevisions);
    }

    protected JSONArray extractRevisions(InputStream inputStreamInstance){
        try {
            JSONArray revisionArray = JsonPath.read(inputStreamInstance,"$..revisions");
            return (JSONArray) revisionArray.getFirst();
        }catch (IOException e) {
            ExceptionHandler.handleIOException(e, "Error while processing user input.");
            return new JSONArray();
        }

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

    public String extractRedirect(InputStream inputStreamInstance){
        try {
            String output = "";
            JSONArray parsedRedirect = JsonPath.read(inputStreamInstance,"$..to");

            if(!parsedRedirect.isEmpty()){
                output = String.format("Redirected to %s",parsedRedirect.getFirst().toString());
            }

            return output;
        }catch (IOException e) {
            ExceptionHandler.handleIOException(e, "Error while processing user input.");
            String failed = "";
            return failed;
        }

    }
}
