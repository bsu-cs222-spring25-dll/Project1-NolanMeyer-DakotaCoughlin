package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
        JSONArray output = new JSONArray();
        try {
            JSONArray revisionArray = JsonPath.read(inputStreamInstance,"$..revisions");
            output = (JSONArray) revisionArray.getFirst();
        }catch (Exception e) {
            ExceptionHandler.handleException(e,"No Wikipedia article could be found!");
        }
        return output;
    }

    protected List<Revision> convertRevisionsToList(JSONArray array) {
        List<Revision> revisionsList = new ArrayList<>();

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
        String output = "";
        try {
            JSONArray parsedRedirect = JsonPath.read(inputStreamInstance,"$..to");

            if(!parsedRedirect.isEmpty()){
                output = String.format("Redirected to %s",parsedRedirect.getFirst().toString());
            }
        }catch (Exception e) {
            ExceptionHandler.handleException(e,"Could not load the input stream!");
        }
        return output;
    }
}
